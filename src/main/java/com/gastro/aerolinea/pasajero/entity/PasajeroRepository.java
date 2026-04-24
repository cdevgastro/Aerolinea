package com.gastro.aerolinea.pasajero.entity;  // o simil

import java.util.List;
import java.util.Optional;

import com.gastro.aerolinea.pasajero.Pasajero;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;  // si usas Lombok

@Entity
@Data
public class PasajeroRepository {

    @Id
    private String id;

    private String nombre;
    private String dni;
    // ... otros campos que necesites

    public Pasajero save(com.gastro.aerolinea.pasajero.entity.Pasajero pasajero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Optional<Pasajero> findById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Pasajero> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public com.gastro.aerolinea.pasajero.entity.Pasajero save(Pasajero pasajero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean existsById(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteById(String id2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}