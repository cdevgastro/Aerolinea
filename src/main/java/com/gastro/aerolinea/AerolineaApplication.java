package com.aerolinea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicación principal del sistema de gestión de Aerolínea.
 *
 * Esta clase inicia el contexto de Spring Boot y carga todas las configuraciones
 * automáticas (@SpringBootApplication).
 *
 * @author TuNombre
 * @version 1.0
 */
@SpringBootApplication
public class AerolineaApplication {

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args argumentos de línea de comandos (no utilizados actualmente)
     */
    public static void main(String[] args) {
        SpringApplication.run(AerolineaApplication.class, args);
    }
}