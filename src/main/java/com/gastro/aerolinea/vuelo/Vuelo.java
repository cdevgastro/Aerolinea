package com.gastro.aerolinea.vuelo;

import com.gastro.aerolinea.avion.Avion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entidad que representa un Vuelo en el sistema.
 */
@Entity
@Table(name = "vuelos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"avion"})   // Evita carga perezosa accidental en logs
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // Cambiado a 'id' (más estándar)

    @NotBlank(message = "El origen no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String origen;

    @NotBlank(message = "El destino no puede estar vacío")
    @Column(nullable = false, length = 100)
    private String destino;

    @NotNull(message = "La hora de salida es obligatoria")
    @Column(nullable = false)
    private LocalDateTime horaSalida;

    @NotNull(message = "La hora de llegada es obligatoria")
    @Column(nullable = false)
    private LocalDateTime horaLlegada;

    @Positive(message = "El precio debe ser mayor a 0")
    @Column(nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVuelo estado;

    @NotNull(message = "El avión es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)           // LAZY es más seguro en relaciones
    @JoinColumn(name = "matricula", nullable = false)
    private Avion avion;

    // Constructor de conveniencia (opcional pero útil)
    public Vuelo(String origen, String destino, LocalDateTime horaSalida,
                 LocalDateTime horaLlegada, Double precio, Avion avion) {
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
        this.avion = avion;
        this.estado = EstadoVuelo.PROGRAMADO;   // Valor por defecto recomendado
    }
}