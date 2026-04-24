package com.gastro.aerolinea.avion;

import com.gastro.aerolinea.reserva.Reserva;
import com.gastro.aerolinea.reserva.mapper.ReservaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvionDTO {

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
    private Long id;
    private String matricula;
    private String modelo;
    private String fabricante;
    private int capacidadPasajeros;
    private int anioFabricacion;
}
