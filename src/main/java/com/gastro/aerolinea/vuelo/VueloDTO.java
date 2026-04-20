package com.gastro.aerolinea.vuelo;

import java.time.LocalDateTime;
import com.gastro.aerolinea.avion.AvionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VueloDTO {

	private Long numeroVuelo;
	private String origen;
	private String destino;
	private LocalDateTime horaSalida;
	private LocalDateTime horaLlegada;
	private Double precio;
	private EstadoVuelo estado;
	private AvionDTO avion;
}
