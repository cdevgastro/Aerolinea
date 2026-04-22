package com.gastro.aerolinea.reserva;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservaDTO {
    

    private String localizador;


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