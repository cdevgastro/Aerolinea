package com.gastro.aerolinea.vuelo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vuelo")
public class VueloController {

    private final VueloService vueloService;

    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @GetMapping
    public ResponseEntity<List<Vuelo>> listarTodos() {
        return ResponseEntity.ok(vueloService.buscarTodos());
    }
    
    @GetMapping("/disponibles")
    public ResponseEntity<List<Vuelo>> listarDisponibles() {
        return ResponseEntity.ok(vueloService.buscarDisponibles());
    }

    @PostMapping
    public ResponseEntity<Vuelo> crear(@RequestBody Vuelo vuelo) {
        Vuelo nuevoVuelo = vueloService.crear(vuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVuelo);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id,
            @RequestBody EstadoVuelo estado
    ) {
    	vueloService.cambiarEstado(id, estado);
    	return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vueloService.eliminar(id);
        return ResponseEntity.noContent().build();
    } 
}