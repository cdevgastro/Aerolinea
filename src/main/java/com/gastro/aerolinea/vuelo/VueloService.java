package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloService {

	List<Vuelo> buscarTodos();
	
	Optional<Vuelo> buscarPorOrigenDestino(String origen, String destino);
	
	List<Vuelo> buscarDisponibles();
	
	Optional<Vuelo> findByNumeroVuelo(Long numeroVuelo);
	
	void cambiarEstado(Long numeroVuelo, EstadoVuelo estado);

	void eliminar(Long numeroVuelo);

	Vuelo crear(Vuelo vuelo);
}