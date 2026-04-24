package com.gastro.aerolinea.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gastro.aerolinea.reserva.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, String> {
}