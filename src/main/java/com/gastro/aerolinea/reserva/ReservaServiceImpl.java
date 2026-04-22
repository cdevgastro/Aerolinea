package com.gastro.aerolinea.reserva;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList; // Para evitar errores de concurrencia
import java.util.stream.Collectors;

@Service
@Transactional // Asegura la integridad de la operación
public class ReservaServiceImpl implements ReservaService {

    /** TODO: 
     * Usar solo el repositorio de Reserva. Crear repositorio reserva como hemos hecho en clases anteriores.
     * Leer el README.md y aplicar los métodos pedidos por el profesor.
     * Eliminar la anotación @Service y @Transactional de la interfaz
     * 
     * */ 
    
}