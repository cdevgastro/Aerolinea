package com.gastro.aerolinea.reserva;
import org.springframework.stereotype.Component;

import com.gastro.aerolinea.vuelo.Vuelo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ReservaMapper {

    public ReservaDTO toDto(Reserva reserva) {
        if (reserva == null) {
            return null;
        }

        ReservaDTO dto = new ReservaDTO();
        dto.setIdReserva(reserva.getIdReserva());
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
        entity.setAsiento(dto.getAsiento());
        entity.setPrecio(dto.getPrecio());
        // estado sigue siendo String
        entity.setEstado(dto.getEstado());

        return entity;
    }

    public List<ReservaDTO> toDtoList(List<Reserva> reservasEntity) {
        if (reservasEntity == null) {
            return null;
        }
        return reservasEntity.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public Optional<List<ReservaDTO>> toDtoOptional(List<Reserva> reservasEntity) {
        if (reservasEntity == null) {
            return Optional.empty();
        }
        return Optional.of(
            reservasEntity.stream()
                .map(this::toDto)
                .toList());
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
