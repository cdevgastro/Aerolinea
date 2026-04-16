import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa la entidad de una Reserva en el sistema.
 * Se identifica al usuario mediante su DNI y datos personales básicos.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    // Identificación del usuario (Clave)
    private String dni;
    private String nombre;
    private String apellido;
    private String correoElectronico;

    // Datos de la reserva
    private String recursoId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoReserva estado;

    /**
     * Enumeración interna para gestionar los estados posibles de la reserva.
     */
    public enum EstadoReserva {
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }
}