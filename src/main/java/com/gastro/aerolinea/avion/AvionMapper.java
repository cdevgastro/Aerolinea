package com.gastro.aerolinea.avion;

public class AvionMapper {

    public static AvionDTO toDTO(Avion avion) {
        if (avion == null) {
            return null;
        }
        return new AvionDTO(
                avion.getId(),
                avion.getMatricula(),
                avion.getModelo() != null ? avion.getModelo().getValue() : null,
                avion.getFabricante(),
                avion.getCapacidadPasajeros(),
                avion.getAnioFabricacion()
        );
    }

    public static Avion toEntity(AvionDTO dto) {
        if (dto == null) {
            return null;
        }
        return Avion.builder()
                .id(dto.getId())
                .matricula(dto.getMatricula())
                .modelo(dto.getModelo() != null ? ModeloAvion.fromValue(dto.getModelo()) : null)
                .fabricante(dto.getFabricante())
                .capacidadPasajeros(dto.getCapacidadPasajeros())
                .anioFabricacion(dto.getAnioFabricacion())
                .build();
    }
}
