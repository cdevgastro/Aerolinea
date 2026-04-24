package com.gastro.aerolinea.pasajero;

import java.time.LocalDate; // O javax.persistence.* según tu versión

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pasajeros")
public class Pasajero {

    @Id // El DNI suele ser la clave primaria, lo que ya lo hace único
    @Column(unique = true, nullable = false)
    private String dni;

    private String nombre;
    private String apellidos;

    @Column(unique = true, nullable = false) // Garantiza que no se repita el email
    private String email;

    private String telefono;
    private LocalDate fechaNacimiento;
    private String nacionalidad;

    public Pasajero(String apellidos, String dni, String email, LocalDate fechaNacimiento, String nacionalidad, String nombre, String telefono) {
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setDni(String dni) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}