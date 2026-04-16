package com.gastro.aerolinea.avion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionService {

    private final AvionRepository avionRepository;

    public AvionService(AvionRepository avionRepository) {
        this.avionRepository = avionRepository;
    }

    public List<AvionDTO> obtenerTodos() {
        return avionRepository.findAll()
                .stream()
                .map(AvionMapper::toDTO)
                .toList();
    }

    public AvionDTO obtenerPorMatricula(String matricula) {
        Avion avion = avionRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Avión no encontrado con matrícula: " + matricula));

        return AvionMapper.toDTO(avion);
    }

    public AvionDTO crear(AvionDTO avionDTO) {
        avionRepository.findByMatricula(avionDTO.getMatricula())
                .ifPresent(a -> {
                    throw new RuntimeException("Ya existe un avión con matrícula: " + avionDTO.getMatricula());
                });

        Avion avion = AvionMapper.toEntity(avionDTO);
        Avion avionGuardado = avionRepository.save(avion);

        return AvionMapper.toDTO(avionGuardado);
    }

    public AvionDTO actualizar(String matricula, AvionDTO avionDTO) {
        Avion avionExistente = avionRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Avión no encontrado con matrícula: " + matricula));

        avionExistente.setMatricula(avionDTO.getMatricula());
        avionExistente.setModelo(
                avionDTO.getModelo() != null ? ModeloAvion.fromValue(avionDTO.getModelo()) : null
        );
        avionExistente.setFabricante(avionDTO.getFabricante());
        avionExistente.setCapacidadPasajeros(avionDTO.getCapacidadPasajeros());
        avionExistente.setAnioFabricacion(avionDTO.getAnioFabricacion());

        Avion avionActualizado = avionRepository.save(avionExistente);
        return AvionMapper.toDTO(avionActualizado);
    }

    public void eliminar(String matricula) {
        Avion avion = avionRepository.findByMatricula(matricula)
                .orElseThrow(() -> new RuntimeException("Avión no encontrado con matrícula: " + matricula));

        avionRepository.delete(avion);
    }
}
