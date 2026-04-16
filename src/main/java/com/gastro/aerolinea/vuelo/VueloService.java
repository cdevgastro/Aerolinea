package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

	List<Vuelo> buscarTodos();
	
	Optional<Vuelo> buscarPorOrigenDestino(String origen, String destino);
	
	List<Vuelo> buscarDisponibles();
	
	Optional<Vuelo> findByNumeroVuelo(String numeroVuelo);
	
	void cambiarEstado(String numeroVuelo, EstadoVuelo estado);
}