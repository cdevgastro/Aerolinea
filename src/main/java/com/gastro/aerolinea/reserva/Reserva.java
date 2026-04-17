 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Representa la entidad de una Reserva en el sistema.
 */
@Entity
@Table(name = "reservas")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"nombre", "apellido", "correoElectronico", "fechaInicio", "fechaFin"})
public class Reserva {

    // ==================== IDENTIFICACIÓN ====================
    @Id
    @NotBlank(message = "El DNI es obligatorio")
    @Size(min = 8, max = 15, message = "El DNI debe tener entre 8 y 15 caracteres")
    @Column(name = "dni", nullable = false, unique = true, length = 15)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false, length = 100)
    private String apellido;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    @Column(nullable = false, length = 150)
    private String correoElectronico;

    // ==================== DATOS DE LA RESERVA ====================
    @NotBlank(message = "El ID del recurso es obligatorio")
    @Column(nullable = false)
    private String recursoId;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column(nullable = false)
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;

    // ==================== CONEXIÓN CON VUELO ====================
    @ManyToOne(optional = false)
    @JoinColumn(name = "vuelo_id", nullable = false)
    @NotNull(message = "La reserva debe estar asociada a un vuelo")
    private Vuelo vuelo;

    // ==================== ENUM ====================
    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }

    // ==================== CONSTRUCTORES ====================

    /** Constructor de conveniencia básico */
    public Reserva(String dni, String nombre, String apellido, String correoElectronico,
                   String recursoId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.recursoId = recursoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoReserva.PENDIENTE;
    }

    /** Constructor recomendado cuando se asocia directamente un vuelo */
    public Reserva(String dni, String nombre, String apellido, String correoElectronico,
                   Vuelo vuelo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.vuelo = vuelo;
        this.recursoId = vuelo != null ? vuelo.getId() : null;   // sincronización opcional
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoReserva.PENDIENTE;
    }
}