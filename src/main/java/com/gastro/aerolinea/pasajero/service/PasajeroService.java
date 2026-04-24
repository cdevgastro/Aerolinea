package com.gastro.aerolinea.pasajero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gastro.aerolinea.pasajero.entity.Pasajero;
import com.gastro.aerolinea.pasajero.entity.PasajeroRepository;

@SuppressWarnings("unused")
@Service
public class PasajeroService {

    private PasajeroRepository pasajeroRepository;

    PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    public PasajeroRepository getPasajeroRepository() {
        return pasajeroRepository;
    }

    public void setPasajeroRepository(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }

    // Guardar un pasajero
    public com.gastro.aerolinea.pasajero.Pasajero guardarPasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    // Buscar pasajero por ID (String)
    public Optional<com.gastro.aerolinea.pasajero.Pasajero> obtenerPasajeroPorId(String id) {
        return pasajeroRepository.findById(id);
    }

    // Listar todos los pasajeros
    public List<com.gastro.aerolinea.pasajero.Pasajero> listarPasajeros() {
        return pasajeroRepository.findAll();
    }

    // Actualizar un pasajero (por ejemplo, por ID)
    public Pasajero actualizarPasajero(String id, Pasajero datosActualizados) {
        return pasajeroRepository.findById(id)
                .map(pasajero -> {
                    pasajero.setNombre(datosActualizados.getNombre());
                    pasajero.setDni(datosActualizados.getDni());
                    // actualiza otros campos según tu modelo Pasajero
                    return pasajeroRepository.save(pasajero);
                })
                .orElseThrow(() -> new RuntimeException("Pasajero no encontrado con id: " + id));
    }

    // Eliminar un pasajero por ID
    public void eliminarPasajero(String id) {
        if (!pasajeroRepository.existsById(id)) {
            throw new RuntimeException("Pasajero no encontrado con id: " + id);
        }
        pasajeroRepository.deleteById(id);
    }
}