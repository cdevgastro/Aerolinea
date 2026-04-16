package com.gastro.aerolinea.pasajero;

import jakarta.persistence.*; // O javax.persistence.* según tu versión
import lombok.*;
import java.time.LocalDate;

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
}