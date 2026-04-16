package com.aerolinea.controller;   // ← Ajusta según tu paquete real
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

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
     * Crea una nueva reserva.
     */
    @PostMapping
    public ResponseEntity<ReservaDTO> crearReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        Reserva nuevaReserva = ReservaMapper.toEntity(reservaDTO);
        Reserva reservaGuardada = reservaService.crearReserva(nuevaReserva);

        ReservaDTO dtoCreado = ReservaMapper.toDTO(reservaGuardada);
        return new ResponseEntity<>(dtoCreado, HttpStatus.CREATED);
    }

    /**
     * Obtiene una reserva por DNI del usuario.
     */
    @GetMapping("/{dni}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorDni(@PathVariable String dni) {
        Optional<Reserva> reservaOpt = reservaService.buscarPorDni(dni);

        return reservaOpt
                .map(reserva -> ResponseEntity.ok(ReservaMapper.toDTO(reserva)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las reservas.
     */
    @GetMapping
    public ResponseEntity<List<ReservaDTO>> obtenerTodas() {
        List<Reserva> reservas = reservaService.listarTodas();
        List<ReservaDTO> dtos = ReservaMapper.toDTOList(reservas);
        return ResponseEntity.ok(dtos);
    }

    /**
     * Confirma una reserva existente.
     */
    @PatchMapping("/{dni}/confirmar")
    public ResponseEntity<ReservaDTO> confirmarReserva(@PathVariable String dni) {
        Reserva reservaConfirmada = reservaService.confirmarReserva(dni);
        return ResponseEntity.ok(ReservaMapper.toDTO(reservaConfirmada));
    }

    /**
     * Cancela una reserva existente.
     */
    @PatchMapping("/{dni}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable String dni) {
        reservaService.cancelarReserva(dni);
        return ResponseEntity.noContent().build();
    }
}