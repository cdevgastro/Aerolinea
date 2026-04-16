package com.gastro.aerolinea.avion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvionDTO {

    private Long id;
    private String matricula;
    private String modelo;
    private String fabricante;
    private int capacidadPasajeros;
    private int anioFabricacion;
}
