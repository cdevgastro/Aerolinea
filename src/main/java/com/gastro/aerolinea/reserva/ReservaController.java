package com.gastro.aerolinea.reserva;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;


    @PostMapping
    public ResponseEntity<ReservaDTO> crear(@Valid @RequestBody ReservaDTO dto) {
        ReservaDTO created = service.crearReserva(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/pasajero/{dni}")
    public ResponseEntity<List<ReservaDTO>> obtenerPorPasajero(@PathVariable String dni) {
        return ResponseEntity.ok(service.listarPorPasajero(dni));
    }

    @GetMapping("/vuelo/{id}")
    public ResponseEntity<List<ReservaDTO>> obtenerPorVuelo(@PathVariable String id) {
        return ResponseEntity.ok(service.listarPorVuelo(id));
    }

    @DeleteMapping("/{localizador}")
    public ResponseEntity<Void> cancelar(@PathVariable String localizador) {
        service.cancelarReserva(localizador);
        return ResponseEntity.noContent().build();
    }
}