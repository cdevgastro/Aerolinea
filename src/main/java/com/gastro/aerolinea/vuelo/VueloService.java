package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

    List<VueloDTO> buscarTodos();

    List<VueloDTO> buscarPorOrigenDestino(String origen, String destino);

    List<VueloDTO> buscarDisponibles();

    Optional<VueloDTO> findByNumeroVuelo(Long numeroVuelo);

    void cambiarEstado(Long numeroVuelo, EstadoVuelo estado);

    void eliminar(Long numeroVuelo);

    VueloDTO crear(VueloDTO vueloDTO);
}