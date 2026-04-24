package com.gastro.aerolinea.reserva;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gastro.aerolinea.vuelo.Vuelo;
@Service
public class ReservaServiceImpl implements ReservaService {

    private final List<ReservaDTO> reservas = new ArrayList<>();
    private final Map<String, Vuelo> vuelos = new HashMap<>();
    private long nextId = 1;
    private static final Random random = new Random();

    @Override
    public ReservaDTO crearReserva(ReservaDTO dto) {
        validarReserva(dto);

        Vuelo vuelo = obtenerVuelo(dto.getNumeroVuelo());
        verificarDisponibilidad(vuelo);

        ReservaDTO nuevaReserva = construirReserva(dto, vuelo);
        vuelo.reservarAsiento();
        reservas.add(nuevaReserva);

        return nuevaReserva;
    }

    @Override
    public List<ReservaDTO> listarPorPasajero(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI requerido");
        }
        return reservas.stream()
                .filter(r -> dni.equals(r.getPasajeroDni()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> listarPorVuelo(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID de vuelo requerido");
        }
        return reservas.stream()
                .filter(r -> id.equals(r.getNumeroVuelo()))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelarReserva(String localizador) {
        if (localizador == null || localizador.trim().isEmpty()) {
            throw new IllegalArgumentException("Localizador requerido");
        }

        ReservaDTO reserva = buscarReservaOLanzarError(localizador);

        if ("CANCELADA".equals(reserva.getEstado())) {
            throw new IllegalStateException("Reserva ya cancelada");
        }

        Vuelo vuelo = vuelos.get(reserva.getNumeroVuelo());
        if (vuelo != null) {
            vuelo.liberarAsiento();
        }
        reserva.setEstado("CANCELADA");
    }

    private void validarReserva(ReservaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Reserva no puede ser nula");
        }
        if (dto.getPasajeroDni() == null || dto.getPasajeroDni().trim().isEmpty()) {
            throw new IllegalArgumentException("DNI del pasajero es obligatorio");
        }
        if (dto.getNombrePasajero() == null || dto.getNombrePasajero().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre del pasajero es obligatorio");
        }
        if (dto.getNumeroVuelo() == null || dto.getNumeroVuelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Número de vuelo es obligatorio");
        }
    }

    private Vuelo obtenerVuelo(String numeroVuelo) {
        Vuelo vuelo = vuelos.get(numeroVuelo);
        if (vuelo == null) {
            throw new IllegalArgumentException("Vuelo no encontrado: " + numeroVuelo);
        }
        return vuelo;
    }

    private void verificarDisponibilidad(Vuelo vuelo) {
        if (vuelo.getAsientosDisponibles() <= 0) {
            throw new IllegalStateException("No hay asientos disponibles en este vuelo");
        }
    }

    private ReservaDTO construirReserva(ReservaDTO dto, Vuelo vuelo) {
        ReservaDTO nuevaReserva = new ReservaDTO();

        nuevaReserva.setIdReserva(String.valueOf(nextId++));
        nuevaReserva.setNumeroVuelo(dto.getNumeroVuelo());
        nuevaReserva.setOrigen(vuelo.getOrigen());
        nuevaReserva.setDestino(vuelo.getDestino());
        nuevaReserva.setFechaSalida(vuelo.getHoraSalida());
        nuevaReserva.setFechaLlegada(vuelo.getHoraLlegada());
        nuevaReserva.setNombrePasajero(dto.getNombrePasajero());
        nuevaReserva.setPasajeroDni(dto.getPasajeroDni());
        nuevaReserva.setAsiento(generarAsiento());
        nuevaReserva.setPrecio(vuelo.getPrecio());
        nuevaReserva.setEstado("CONFIRMADA");
        return nuevaReserva;
    }

    private ReservaDTO buscarReservaOLanzarError(String localizador) {
        return reservas.stream()
                .filter(r -> localizador.equals(r.getIdReserva()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada: " + localizador));
    }

    private String generarAsiento() {
        char letra = (char) ('A' + random.nextInt(6));
        int numero = random.nextInt(30) + 1;
        return String.format("%02d%c", numero, letra);
    }

   
}