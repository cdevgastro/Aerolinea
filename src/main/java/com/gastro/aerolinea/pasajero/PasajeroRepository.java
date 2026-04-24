package com.gastro.aerolinea.pasajero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gastro.aerolinea.pasajero.entity.Pasajero;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, String> {
}