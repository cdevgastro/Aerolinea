package com.gastro.aerolinea.avion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AvionServiceImpl implements AvionService {

    private final AvionRepository avionRepository;

    /*
        Lista todos los aviones registrados en la base de datos.
        Convierte cada entidad Avion a AvionDTO antes de devolverla.
        @return Lista de todos los aviones en formato DTO.
    */

    @Override
    @Transactional(readOnly = true)
    public List<AvionDTO> obtenerTodos() {
        return avionRepository.findAll().stream()
                .map(AvionMapper::toDTO)
                .collect(Collectors.toList());
    }

    /*
        Busca un avión por su matrícula única.
        Lanza una excepcion IllegalArgumentException si no lo encuentra.
        @param matricula La matrícula del avión a buscar.
        @return El avión encontrado en formato DTO.
        @throws IllegalArgumentException si no existe un avión con esa matrícula.
    */

    @Override
    @Transactional(readOnly = true)
    public AvionDTO obtenerPorMatricula(String matricula) {
        Avion avion = avionRepository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Avión no encontrado con matrícula: " + matricula));
        return AvionMapper.toDTO(avion);
    }

    /*
        Registra un nuevo avión en el sistema.
        Verifica que la matrícula no esté duplicada antes de guardarlo.
        @param avionDTO Los datos del avión a crear.
        @return El avión creado en formato DTO.
    */

    @Override
    public AvionDTO crear(AvionDTO avionDTO) {
        if (avionRepository.findByMatricula(avionDTO.getMatricula()).isPresent()) {
            throw new IllegalArgumentException(
                    "Ya existe un avión con la matrícula: " + avionDTO.getMatricula());
        }
        Avion avion = AvionMapper.toEntity(avionDTO);
        return AvionMapper.toDTO(avionRepository.save(avion));
    }

    /*
        Actualiza los datos de un avión existente.
        Solo actualiza los campos que no sean nulos o mayores a 0 en el DTO.
        La matrícula no se puede cambiar.
        @param matricula La matrícula del avión a actualizar.
        @param avionDTO  Los nuevos datos del avión.
        @return El avión actualizado en formato DTO.
    */

    @Override
    public AvionDTO actualizar(String matricula, AvionDTO avionDTO) {
        Avion avion = avionRepository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Avión no encontrado con matrícula: " + matricula));

        if (avionDTO.getModelo() != null) {
            avion.setModelo(ModeloAvion.fromValue(avionDTO.getModelo()));
        }
        if (avionDTO.getFabricante() != null) {
            avion.setFabricante(avionDTO.getFabricante());
        }
        if (avionDTO.getCapacidadPasajeros() > 0) {
            avion.setCapacidadPasajeros(avionDTO.getCapacidadPasajeros());
        }
        if (avionDTO.getAnioFabricacion() > 0) {
            avion.setAnioFabricacion(avionDTO.getAnioFabricacion());
        }

        return AvionMapper.toDTO(avionRepository.save(avion));
    }

    /*
        Elimina un avión del sistema por su matrícula.
        Lanza una excepcion si el avión no existe.
        @param matricula La matrícula del avión a eliminar
    */

    @Override
    public void eliminar(String matricula) {
        if (!avionRepository.findByMatricula(matricula).isPresent()) {
            throw new IllegalArgumentException(
                    "Avión no encontrado con matrícula: " + matricula);
        }
        avionRepository.delete(avionRepository.findByMatricula(matricula).get());
    }
}

