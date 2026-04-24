package com.gastro.aerolinea.reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.gastro.aerolinea.pasajero.Pasajero;
import com.gastro.aerolinea.vuelo.Vuelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase Reserva con Lombok
 * Solo propiedades + anotaciones Lombok
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    
    @Id  // ← Clave primaria obligatoria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← Auto-incremental
    private String idReserva;
    
    private String localizador;
    private String asiento;
    private double precio;
    private String estado;

    @ManyToOne
	@JoinColumn(name = "numero_vuelo", nullable = false)
	private Vuelo vuelo;

    @ManyToOne
	@JoinColumn(name = "dni_pasajero", nullable = false)
	private Pasajero pasajero;

}