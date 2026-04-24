package com.gastro.aerolinea.avion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/aviones")
public class AvionController {

    @Autowired
    private AvionService avionService;

    @GetMapping
    public ResponseEntity<List<AvionDTO>> listarTodos() {
        return ResponseEntity.ok(avionService.obtenerTodos());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AvionDTO> obtenerPorMatricula(@PathVariable String matricula) {
        return ResponseEntity.ok(avionService.obtenerPorMatricula(matricula));
    }

    @PostMapping
    public ResponseEntity<AvionDTO> crear(@Valid @RequestBody AvionDTO avionDTO) {
        AvionDTO nuevoAvion = avionService.crear(avionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAvion);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<AvionDTO> actualizar(
            @PathVariable String matricula,
            @RequestBody AvionDTO avionDTO
    ) {
        return ResponseEntity.ok(avionService.actualizar(matricula, avionDTO));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> eliminar(@PathVariable String matricula) {
        avionService.eliminar(matricula);
        return ResponseEntity.noContent().build();
    }
}
