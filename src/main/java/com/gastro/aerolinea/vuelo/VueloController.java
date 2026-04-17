package com.gastro.aerolinea.vuelo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vuelo")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping
    public ResponseEntity<List<VueloDTO>> listarTodos() {
        return ResponseEntity.ok(vueloService.buscarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<VueloDTO>> listarDisponibles() {
        return ResponseEntity.ok(vueloService.buscarDisponibles());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<VueloDTO>> buscarOrigenDestino(
            @RequestParam String origen,
            @RequestParam String destino) {

        return ResponseEntity.ok(
                vueloService.buscarPorOrigenDestino(origen, destino)
        );
    }

    @PostMapping
    public ResponseEntity<VueloDTO> crear(@RequestBody VueloDTO vueloDTO) {
        VueloDTO nuevo = vueloService.crear(vueloDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id,
            @RequestBody EstadoVuelo estado
    ) {
        vueloService.cambiarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vueloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}