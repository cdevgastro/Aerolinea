package com.gastro.aerolinea.reserva;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservaMapper {

    public ReservaDTO toDto(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        ReservaDTO dto = new ReservaDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setNumeroVuelo(reserva.getNumeroVuelo());
        dto.setOrigen(reserva.getOrigen());
        dto.setDestino(reserva.getDestino());
        dto.setFechaSalida(reserva.getFechaSalida());
        dto.setFechaLlegada(reserva.getFechaLlegada());
        dto.setNombrePasajero(reserva.getNombrePasajero());
        dto.setDniPasajero(reserva.getDniPasajero());
        dto.setAsiento(reserva.getAsiento());
        dto.setPrecio(reserva.getPrecio());

        // estado es String en la entidad
        dto.setEstado(reserva.getEstado());

        return dto;
    }

    public Reserva toEntity(ReservaDTO dto) {
        if (dto == null) {
            return null;
        }

        Reserva entity = new Reserva();
        entity.setIdReserva(dto.getIdReserva());
        entity.setNumeroVuelo(dto.getNumeroVuelo());
        entity.setOrigen(dto.getOrigen());
        entity.setDestino(dto.getDestino());
        entity.setFechaSalida(dto.getFechaSalida());
        entity.setFechaLlegada(dto.getFechaLlegada());
        entity.setNombrePasajero(dto.getNombrePasajero());
        entity.setDniPasajero(dto.getDniPasajero());
        entity.setAsiento(dto.getAsiento());
        entity.setPrecio(dto.getPrecio());

        // estado sigue siendo String
        entity.setEstado(dto.getEstado());

        return entity;
    }

    public List<ReservaDTO> toDtoList(List<Reserva> reservas) {
        if (reservas == null) {
            return null;
        }
        return reservas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Reserva> toEntityList(List<ReservaDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
