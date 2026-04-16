import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones de Reservas.
 * Expone los servicios a través de endpoints HTTP.
 */
@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    /**
     * Crea una nueva reserva a partir de los datos recibidos en el DTO.
     */
    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@RequestBody ReservaDTO reservaDTO) {
        // Convertimos el DTO a entidad para procesar en el servicio
        Reserva nuevaReserva = ReservaMapper.toEntity(reservaDTO);
        
        // El servicio maneja la lógica de negocio y persistencia
        Reserva reservaGuardada = reservaService.crearReserva(nuevaReserva);
        
        // Retornamos el DTO de la reserva creada
        return new ResponseEntity<>(ReservaMapper.toDTO(reservaGuardada), HttpStatus.CREATED);
    }

    /**
     * Obtiene una reserva por el DNI del usuario.
     */
    @GetMapping("/{dni}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorDni(@PathVariable String dni) {
        return reservaService.buscarPorDni(dni)
                .map(reserva -> ResponseEntity.ok(ReservaMapper.toDTO(reserva)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene el listado completo de todas las reservas.
     */
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        return ResponseEntity.ok(ReservaMapper.toDTOList(reservas));
    }

    /**
     * Confirma una reserva existente cambiando su estado.
     */
    @PatchMapping("/{dni}/confirmar")
    public ResponseEntity<ReservaDTO> confirmarReserva(@PathVariable String dni) {
        Reserva reservaConfirmada = reservaService.confirmarReserva(dni);
        return ResponseEntity.ok(ReservaMapper.toDTO(reservaConfirmada));
    }

    /**
     * Cancela una reserva existente.
     */
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable String dni) {
        reservaService.cancelarReserva(dni);
        return ResponseEntity.noContent().build();
    }
}