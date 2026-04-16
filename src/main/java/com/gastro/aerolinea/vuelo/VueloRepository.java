package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

	Optional<Vuelo> findByOrigenAndDestino(String origen, String destino);

	@Query("SELECT v FROM Vuelo v WHERE v.estado = 'DISPONIBLE'")
	List<Vuelo> buscarDisponibles();
	
	Optional<Vuelo> findByNumeroVuelo(Long numeroVuelo);
}