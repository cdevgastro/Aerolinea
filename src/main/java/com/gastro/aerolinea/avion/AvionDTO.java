package com.gastro.aerolinea.avion;

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
