import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Objeto de Transferencia de Datos (DTO) para la entidad Reserva.
 * Se utiliza para mover datos entre la capa de controlador y la capa de servicio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private String dni;
    private String nombreCompleto; // Ejemplo de campo transformado para la vista
    private String correoElectronico;
    
    private String recursoId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String estado; // Generalmente se pasa como String en el DTO para facilitar la serialización JSON

}