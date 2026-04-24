package com.gastro.aerolinea.reserva;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gastro.aerolinea.vuelo.Vuelo;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {

    List<Reserva> findByPasajeroDni(String dni);

    Optional<Vuelo> findByVueloNumeroVuelo(Long id);

    Reserva finByLocalizador(String localizador);
}

