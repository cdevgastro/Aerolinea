import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase encargada de transformar objetos entre la capa de persistencia (Reserva)
 * y la capa de transferencia de datos (ReservaDTO).
 */
public class ReservaMapper {

    /**
     * Convierte una entidad Reserva a un ReservaDTO.
     * Realiza la lógica de negocio necesaria como concatenar el nombre completo.
     */
    public static ReservaDTO toDTO(Reserva entidad) {
        if (entidad == null) {
            return null;
        }

        return ReservaDTO.builder()
                .dni(entidad.getDni())
                .nombreCompleto(entidad.getNombre() + " " + entidad.getApellido())
                .correoElectronico(entidad.getCorreoElectronico())
                .recursoId(entidad.getRecursoId())
                .fechaInicio(entidad.getFechaInicio())
                .fechaFin(entidad.getFechaFin())
                .estado(entidad.getEstado() != null ? entidad.getEstado().name() : null)
                .build();
    }

    /**
     * Convierte un ReservaDTO a una entidad Reserva.
     * Nota: El mapeo del nombre/apellido inverso suele requerir lógica adicional 
     * o datos originales si el DTO solo tiene nombreCompleto.
     */
    public static Reserva toEntity(ReservaDTO dto) {
        if (dto == null) {
            return null;
        }

        // Dividimos el nombre completo de forma básica (asumiendo Espacio como separador)
        String[] nombres = dto.getNombreCompleto().split(" ", 2);
        String nombre = nombres.length > 0 ? nombres[0] : "";
        String apellido = nombres.length > 1 ? nombres[1] : "";

        return Reserva.builder()
                .dni(dto.getDni())
                .nombre(nombre)
                .apellido(apellido)
                .correoElectronico(dto.getCorreoElectronico())
                .recursoId(dto.getRecursoId())
                .fechaInicio(dto.getFechaInicio())
                .fechaFin(dto.getFechaFin())
                .estado(dto.getEstado() != null ? Reserva.EstadoReserva.valueOf(dto.getEstado()) : null)
                .build();
    }

    /**
     * Convierte una lista de entidades a una lista de DTOs.
     */
    public static List<ReservaDTO> toDTOList(List<Reserva> entidades) {
        return entidades.stream()
                .map(ReservaMapper::toDTO)
                .collect(Collectors.toList());
    }
}