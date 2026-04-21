package com.gastro.aerolinea.vuelo;

import java.time.LocalDateTime;
import com.gastro.aerolinea.avion.AvionDTO;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message = "El origen no puede estar vacío")
	private String origen;
	@NotBlank(message = "El destino no puede estar vacío")
	private String destino;
	@NotNull(message = "La hora de salida es obligatoria")
	@Future(message = "La fecha es anterior a la fecha actual")
	private LocalDateTime horaSalida;
	@NotNull(message = "La hora de llegada es obligatoria")
	@Future(message = "La fecha es anterior a la fecha actual")
	private LocalDateTime horaLlegada;
	@NotNull(message = "El precio es obligatorio")
	@PositiveOrZero(message = "El precio no puede ser negativo")
	private Double precio;
	@NotNull(message = "El estado es obligatorio")
	private EstadoVuelo estado;
	@NotNull(message = "El avión no puede estar vacío")
	private AvionDTO avion;
}
