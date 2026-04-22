package com.gastro.aerolinea.reserva;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaAplicacion {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaAplicacion(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    public List<ReservaDTO> obtenerTodas() {
        return reservaMapper.toDtoList(reservaRepository.findAll());
    }

    public ReservaDTO obtenerPorId(String id) {
        return reservaRepository.findById(id)
                .map(reservaMapper::toDto)
                .orElse(null);
    }

    public ReservaDTO crear(ReservaDTO dto) {
        Reserva reserva = reservaMapper.toEntity(dto);
        Reserva guardada = reservaRepository.save(reserva);
        return reservaMapper.toDto(guardada);
    }

    public ReservaDTO actualizar(String id, ReservaDTO dto) {
        if (!reservaRepository.existsById(id)) {
            return null;
        }

        Reserva reserva = reservaMapper.toEntity(dto);
        reserva.setIdReserva(id); // ← ahora coincide con String

        Reserva actualizada = reservaRepository.save(reserva);
        return reservaMapper.toDto(actualizada);
    }

    public boolean eliminar(String id) {
        if (!reservaRepository.existsById(id)) {
            return false;
        }
        reservaRepository.deleteById(id);
        return true;
    }
}

