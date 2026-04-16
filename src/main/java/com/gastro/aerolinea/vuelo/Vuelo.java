package com.gastro.aerolinea.vuelo;

import java.time.LocalDateTime;

import com.gastro.aerolinea.avion.Avion;
import org.springframework.format.annotation.DateTimeFormat;
import com.gastro.aerolinea.avion.Avion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vuelos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vuelo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroVuelo;

	@NotBlank(message = "El origen no puede estar vacío")
	@Column(nullable = false, length = 100)
	private String origen;

	@NotBlank(message = "El destino no puede estar vacío")
	@Column(nullable = false, length = 100)
	private String destino;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime horaSalida;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime horaLlegada;

	@Positive(message = "El precio debe ser mayor a 0")
	@Column(nullable = false)
	private Double precio;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoVuelo estado;

	@NotNull(message = "El avión no puede estar vacío")
	@ManyToOne
	@JoinColumn(name = "matricula", nullable = false)
	private Avion avion;
}