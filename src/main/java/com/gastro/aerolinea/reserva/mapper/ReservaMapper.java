// src/main/java/com/gastro/aerolinea/reserva/mapper/ReservaMapper.java
package com.gastro.aerolinea.reserva.mapper;

import com.gastro.aerolinea.reserva.Reserva;

public class ReservaMapper {

    private ReservaMapper() {
        /* This utility class should not be instantiated */
    }

    public static ReservaDto toDto(Reserva reserva) {
        if (reserva == null) {
            return null;
        }
        ReservaDto dto = new ReservaDto();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setNumeroVuelo(reserva.getNumeroVuelo());
        dto.setOrigen(reserva.getOrigen());
        dto.setDestino(reserva.getDestino());
        dto.setFechaSalida(reserva.getFechaSalida());
        dto.setFechaLlegada(reserva.getFechaLlegada());      // ✔️ aquí estaba mal: no era fechaSalida dos veces
        dto.setNombrePasajero(reserva.getNombrePasajero());
        dto.setDniPasajero(reserva.getDniPasajero());        // ✔️ aquí estaba mal: no era nombrePasajero de nuevo
        dto.setAsiento(reserva.getAsiento());
        dto.setPrecio(reserva.getPrecio());
        dto.setEstado(reserva.getEstado());
        return dto;
    }

    public static Reserva toEntity(ReservaDto dto) {
        if (dto == null) {
            return null;
        }
        Reserva reserva = new Reserva();
        reserva.setIdReserva(dto.getIdReserva());
        reserva.setNumeroVuelo(dto.getNumeroVuelo());
        reserva.setOrigen(dto.getOrigen());
        reserva.setDestino(dto.getDestino());
        reserva.setFechaSalida(dto.getFechaSalida());
        reserva.setFechaLlegada(dto.getFechaLlegada());
        reserva.setNombrePasajero(dto.getNombrePasajero());
        reserva.setDniPasajero(dto.getDniPasajero());
        reserva.setAsiento(dto.getAsiento());
        reserva.setPrecio(dto.getPrecio());
        reserva.setEstado(dto.getEstado());
        return reserva;
    }
}