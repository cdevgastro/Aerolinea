package com.gastro.aerolinea.avion;

import com.gastro.aerolinea.reserva.Reserva;
import com.gastro.aerolinea.reserva.mapper.ReservaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

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

    @NotBlank(message = "La matrícula es obligatoria")
    private String matricula;
    
    @NotNull(message = "El modelo es obligatorio")
    private String modelo;

    @NotBlank(message = "El fabricante es obligatorio")
    private String fabricante;
    
    @NotNull(message = "La capacidad de pasajeros es obligatoria")
    private int capacidadPasajeros;

    @Min(value = 1900, message = "Año inválido")
    private int anioFabricacion;
}
