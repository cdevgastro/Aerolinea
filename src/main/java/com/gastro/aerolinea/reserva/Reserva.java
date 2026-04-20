package com.gastro.aerolinea.reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Clase Reserva con Lombok
 * Solo propiedades + anotaciones Lombok
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    private String idReserva;
    private String numeroVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String nombrePasajero;
    private String dniPasajero;
    private String asiento;
    private double precio;
    private String estado;

}