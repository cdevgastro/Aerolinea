package com.gastro.aerolinea.reserva;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservaDTO {
    private String localizador;

    @NotBlank(message = "El DNI del pasajero es obligatorio")
    private String pasajeroDni;

    @NotBlank(message = "El número de vuelo es obligatorio")
    private String numeroVuelo;
}