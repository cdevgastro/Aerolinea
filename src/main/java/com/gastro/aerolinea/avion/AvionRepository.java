package com.gastro.aerolinea.avion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvionRepository extends JpaRepository<Avion, Long> {

    Optional<Avion> findByMatricula(String matricula);
}
