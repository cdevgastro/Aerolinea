package com.gastro.aerolinea.vuelo;

import com.gastro.aerolinea.avion.AvionMapper;
import org.springframework.stereotype.Component;

@Component
public class VueloMapper {

    /**
     * Convierte de Entidad (Vuelo) a DTO (VueloDTO)
     */
    public VueloDTO toDTO(Vuelo vuelo) {
        if (vuelo == null) {
            return null;
        }

        return VueloDTO.builder()
                .numeroVuelo(vuelo.getNumeroVuelo())
                .origen(vuelo.getOrigen())
                .destino(vuelo.getDestino())
                .horaSalida(vuelo.getHoraSalida())
                .horaLlegada(vuelo.getHoraLlegada())
                .precio(vuelo.getPrecio())
                .estado(vuelo.getEstado() != null ? EstadoVuelo.valueOf(vuelo.getEstado().name()) : null)
                .avion(AvionMapper.toDTO(vuelo.getAvion()))
                .build();
    }

    /**
     * Convierte de DTO (VueloDTO) a Entidad (Vuelo)
     */
    public Vuelo toEntity(VueloDTO dto) {
        if (dto == null) {
            return null;
        }

        return Vuelo.builder()
                .numeroVuelo(dto.getNumeroVuelo())
                .origen(dto.getOrigen())
                .destino(dto.getDestino())
                .horaSalida(dto.getHoraSalida())
                .horaLlegada(dto.getHoraLlegada())
                .precio(dto.getPrecio())
                .estado(dto.getEstado() != null ? EstadoVuelo.valueOf(dto.getEstado().name()) : null)
                .avion(AvionMapper.toEntity(dto.getAvion()))
                .build();
    }
}
