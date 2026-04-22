package com.gastro.aerolinea.reserva;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    /**
     * Busca todas las reservas asociadas a un DNI de pasajero.
     * Requisito: GET /api/reservas/pasajero/{dni}
     */
    List<Reserva> findByDniPasajero(String dniPasajero);

    /**
     * Busca todas las reservas de un vuelo específico.
     * Requisito: GET /api/reservas/vuelo/{id}
     */
    List<Reserva> findByNumeroVuelo(String numeroVuelo);

    /**
     * Busca una reserva por su localizador único (String).
     * Requisito: DELETE /api/reservas/{localizador}
     */
    Optional<Reserva> findByIdReserva(String idReserva);

    /**
     * Elimina una reserva por su localizador único.
     */
    void deleteByIdReserva(String idReserva);
}