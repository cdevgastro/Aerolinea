package com.gastro.aerolinea.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional // Asegura la integridad de la operación
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository repository;
    @Autowired
    private ReservaMapper mapper;   



    @Override
    public ReservaDTO crearReserva(ReservaDTO dto) {
        Reserva reserva = mapper.toEntity(dto);
        reserva = repository.save(reserva);
        return mapper.toDTO(reserva);
    }

    @Override
    public List<ReservaDTO> listarPorPasajero(String dni) {
        List<Reserva> reservas = repository.findByDniPasajero(dni);
        return reservas.stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<ReservaDTO> listarPorVuelo(String id) {
        List<Reserva> reservas = repository.findByNumeroVuelo(id);
        return reservas.stream().map(mapper::toDTO).toList();
    }

    @Override
    public void cancelarReserva(String localizador) {
        repository.deleteByIdReserva(localizador);
    }

    /** TODO: 
     * Usar solo el repositorio de Reserva. Crear repositorio reserva como hemos hecho en clases anteriores.
     * Leer el README.md y aplicar los métodos pedidos por el profesor.
     * Eliminar la anotación @Service y @Transactional de la interfaz
     * 
     * */ 
    
}