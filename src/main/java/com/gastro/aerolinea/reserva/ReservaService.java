import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio que gestiona la lógica de negocio para las Reservas.
 */
@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    /**
     * Crea una nueva reserva con validaciones básicas.
     *
     * @param reserva Objeto Reserva con los datos a registrar
     * @return La reserva guardada con su ID generado
     * @throws IllegalArgumentException si las fechas no son coherentes
     */
    public Reserva crearReserva(Reserva reserva) {
        // Validar que las fechas sean coherentes
        if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        // Establecer estado inicial si no viene definido
        if (reserva.getEstado() == null) {
            reserva.setEstado(Reserva.EstadoReserva.PENDIENTE);
        }

        return reservaRepository.save(reserva);
    }

    /**
     * Busca una reserva por su DNI.
     *
     * @param dni DNI del usuario
     * @return Optional con la reserva si existe
     */
    public Optional<Reserva> buscarPorDni(String dni) {
        return reservaRepository.findByDni(dni);
    }

    /**
     * Retorna todas las reservas registradas.
     *
     * @return Lista de todas las reservas
     */
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    /**
     * Confirma una reserva existente.
     *
     * @param dni DNI de la reserva a confirmar
     * @return La reserva actualizada con estado CONFIRMADA
     * @throws ReservaNotFoundException si no se encuentra la reserva
     */
    public Reserva confirmarReserva(String dni) {
        Reserva reserva = reservaRepository.findByDni(dni)
                .orElseThrow(() -> new ReservaNotFoundException("No se encontró ninguna reserva para el DNI: " + dni));

        reserva.setEstado(Reserva.EstadoReserva.CONFIRMADA);
        return reservaRepository.save(reserva);
    }

    /**
     * Cancela/elimina una reserva del sistema.
     *
     * @param dni DNI de la reserva a cancelar
     * @throws ReservaNotFoundException si no se encuentra la reserva
     */
    public void cancelarReserva(String dni) {
        Reserva reserva = reservaRepository.findByDni(dni)
                .orElseThrow(() -> new ReservaNotFoundException("No se encontró ninguna reserva para cancelar con DNI: " + dni));

        reservaRepository.delete(reserva);
    }
}