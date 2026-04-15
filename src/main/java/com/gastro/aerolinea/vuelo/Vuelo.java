package com.gastro.aerolinea.vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

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

	public enum EstadoVuelo {
		DISPONIBLE, PROGRAMADO, EN_VUELO, ATERRIZADO, CANCELADO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroVuelo;

	@Column(nullable = false, length = 100)
	private String origen;

	@Column(nullable = false, length = 100)
	private String destino;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime horaSalida;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(nullable = false)
	private LocalDateTime horaLlegada;

	@Column(nullable = false)
	private Double precio;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoVuelo estado;

	@ManyToOne
	@JoinColumn(name = "matricula", nullable = false)
	private Avion avion;
}
