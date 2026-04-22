package com.gastro.aerolinea.reserva;

import java.util.List;


public interface ReservaService {

    /**
     * Crea una nueva reserva a partir de un DTO.
     * @param dto Datos de la reserva a crear.
     * @return DTO de la reserva creada.
     */
    ReservaDTO crearReserva(ReservaDTO dto);

    /**
     * Lista todas las reservas de un pasajero por su DNI.
     * @param dni DNI del pasajero.
     * @return Lista de reservas del pasajero.
     */
    List<ReservaDTO> listarPorPasajero(String dni);

    /**
     * Lista todas las reservas de un vuelo por su identificador.
     * @param id Identificador del vuelo.
     * @return Lista de reservas del vuelo.
     */
    List<ReservaDTO> listarPorVuelo(String id);

    /**
     * Cancela una reserva por su localizador.
     * @param localizador Localizador de la reserva.
     */
    void cancelarReserva(String localizador);
}