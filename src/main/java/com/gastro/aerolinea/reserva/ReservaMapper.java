package com.gastro.aerolinea.reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    /**
     * Convierte de Entidad (Reserva) a DTO (ReservaDTO)
     */
    public ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        return ReservaDTO.builder()
                .idReserva(reserva.getIdReserva())
                .dniPasajero(reserva.getDniPasajero())
                .numeroVuelo(reserva.getNumeroVuelo())
                .build();
    }


    /**
     * Convierte de DTO (ReservaDTO) a Entidad (Reserva)
     */
    public Reserva toEntity(ReservaDTO dto) {
        if (dto == null) {
            return null;
        }

        return Reserva.builder()
                .idReserva(dto.getIdReserva())
                .dniPasajero(dto.getDniPasajero())
                .numeroVuelo(dto.getNumeroVuelo())
                .build();
    }
}
