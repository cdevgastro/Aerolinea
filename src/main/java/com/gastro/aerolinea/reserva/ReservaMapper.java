package com.gastro.aerolinea.reserva;

import org.springframework.stereotype.Component;

/**
 * Mapper responsable de convertir entidades Reserva en ReservaDTO.
 * 
 * Evita NullPointerException al acceder a relaciones (Vuelo y Pasajero).
 */
@Component
public class ReservaMapper {

    /**
     * Convierte una entidad Reserva a su correspondiente DTO.
     *
     * @param reserva Entidad de la base de datos
     * @return ReservaDTO con los datos mapeados, o null si la reserva es null
     */
    public ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        ReservaDTO dto = new ReservaDTO();

        // Campos básicos
        dto.setLocalizador(reserva.getLocalizador());

        // Mapeo seguro de la relación con Vuelo
        if (reserva.getVuelo() != null) {
            dto.setNumeroVuelo(reserva.getVuelo().getNumeroVuelo());
        } else {
            dto.setNumeroVuelo(null);
        }

        // Mapeo seguro de la relación con Pasajero
        if (reserva.getPasajero() != null) {
            dto.setPasajeroDni(reserva.getPasajero().getDni());
        } else {
            dto.setPasajeroDni(null);
        }

        return dto;
    }
}