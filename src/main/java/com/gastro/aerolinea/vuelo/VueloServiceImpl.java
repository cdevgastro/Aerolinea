package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository;
    private final VueloMapper vueloMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarTodos() {
        return vueloRepository.findAll()
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarPorOrigenDestino(String origen, String destino) {
        return vueloRepository.findByOrigenAndDestino(origen, destino)
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarDisponibles() {
        return vueloRepository.buscarDisponibles()
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VueloDTO> findByNumeroVuelo(Long numeroVuelo) {
        return vueloRepository.findByNumeroVuelo(numeroVuelo)
                .map(vueloMapper::toDTO);
    }

    @Override
    public void cambiarEstado(Long numeroVuelo, EstadoVuelo estado) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        vuelo.setEstado(estado);
        vueloRepository.save(vuelo);
    }

    @Override
    public void eliminar(Long numeroVuelo) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        vueloRepository.delete(vuelo);
    }

    @Override
    public VueloDTO crear(VueloDTO vueloDTO) {
        Vuelo vuelo = vueloMapper.toEntity(vueloDTO);

        vueloRepository.save(vuelo);

        return vueloMapper.toDTO(vuelo);
    }
}