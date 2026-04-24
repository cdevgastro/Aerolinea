package com.gastro.aerolinea.reserva;

import com.gastro.aerolinea.pasajero.PasajeroDTO;
import com.gastro.aerolinea.vuelo.VueloDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReservaDTO {
      
    @NotBlank(message = "El ID de la reserva es obligatorio")
    private String idReserva; 

    @NotBlank(message = "El asiento es obligatorio")
    private String asiento;
    @NotBlank(message = "El precio es obligatorio")
    private double precio;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El localizador es obligatorio")
    private String localizador;

    private VueloDTO vuelo;
    private PasajeroDTO pasajero;

}

