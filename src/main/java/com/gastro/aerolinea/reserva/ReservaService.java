import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona la lógica de negocio para las Reservas.
 * Implementa las operaciones definidas en el ReservaController.
 */
@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    /**
     * Procesa la creación de una reserva.
     * Aquí se pueden añadir validaciones como verificar si el recurso está libre.
     */
    public Reserva crearReserva(Reserva reserva) {
        // Validar que las fechas sean coherentes
        if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la de fin");
        }

        // Establecer estado inicial si no viene definido
        if (reserva.getEstado() == null) {
            reserva.setEstado(Reserva.EstadoReserva.PENDIENTE);
        }

        return reservaRepository.save(reserva);
    }

    /**
     * Busca una reserva específica utilizando el DNI como identificador.
     */
    public Optional<Reserva> buscarPorDni(String dni) {
        return reservaRepository.findByDni(dni);
    }

    /**
     * Retorna la lista de todas las reservas registradas.
     */
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    /**
     * Cambia el estado de una reserva a CONFIRMADA.
     */
    public Reserva confirmarReserva(String dni) {
        Reserva reserva = reservaRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada para el DNI: " + dni));
        
        reserva.setEstado(Reserva.EstadoReserva.CONFIRMADA);
        return reservaRepository.save(reserva);
    }

    /**
     * Elimina o cancela una reserva del sistema.
     */
    public void cancelarReserva(String dni) {
        Reserva reserva = reservaRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("No se pudo encontrar la reserva para eliminar"));
        
        reservaRepository.delete(reserva);
    }
}