package com.gastro.aerolinea.reserva;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservaDTO {
      
    @NotBlank(message = "El ID de la reserva es obligatorio")
    private String idReserva; 
    @NotBlank(message = "El origen es obligatorio")
    private String origen;
    @NotBlank(message = "El destino es obligatorio")
    private String destino;
    @NotBlank(message = "La fecha de salida es obligatoria")
    private LocalDateTime fechaSalida;
    @NotBlank(message = "La fecha de llegada es obligatoria")
    private LocalDateTime fechaLlegada;
    @NotBlank(message = "El nombre del pasajero es obligatorio")
    private String nombrePasajero;  
    @NotBlank(message = "El asiento es obligatorio")
    private String asiento;
    @NotBlank(message = "El precio es obligatorio")
    private double precio;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;


    private String localizador;

    @NotBlank(message = "El DNI del pasajero es obligatorio")
    private String pasajeroDni;

    @NotBlank(message = "El número de vuelo es obligatorio")
    private String numeroVuelo;


}

