package com.gastro.aerolinea.reserva;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

interface ReservaService {
    ReservaDTO crearReserva(ReservaDTO dto);
    List<ReservaDTO> listarPorPasajero(String dni);
    List<ReservaDTO> listarPorVuelo(String id);
    void cancelarReserva(String localizador);
}

class ReservaDTO {
    private String idReserva;
    private String numeroVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String nombrePasajero;
    private String dniPasajero;
    private String asiento;
    private double precio;
    private String estado;

    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }

    public String getNumeroVuelo() { return numeroVuelo; }
    public void setNumeroVuelo(String numeroVuelo) { this.numeroVuelo = numeroVuelo; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public String getNombrePasajero() { return nombrePasajero; }
    public void setNombrePasajero(String nombrePasajero) { this.nombrePasajero = nombrePasajero; }

    public String getDniPasajero() { return dniPasajero; }
    public void setDniPasajero(String dniPasajero) { this.dniPasajero = dniPasajero; }

    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

@Service
public class ReservaServiceImpl implements ReservaService {

    private final List<ReservaDTO> reservas = new ArrayList<>();
    private final Map<String, Vuelo> vuelos = new HashMap<>();
    private long nextId = 1;
    private static final Random random = new Random();

    public ReservaServiceImpl() {
        inicializarVuelos();
    }

    private void inicializarVuelos() {
        vuelos.put("IB3174", new Vuelo(
            "IB3174", "MAD", "BCN",
            LocalDateTime.of(2026, 4, 22, 8, 0),
            LocalDateTime.of(2026, 4, 22, 9, 30),
            120
        ));

        vuelos.put("IB2051", new Vuelo(
            "IB2051", "SVQ", "AGP",
            LocalDateTime.of(2026, 4, 22, 12, 0),
            LocalDateTime.of(2026, 4, 22, 12, 45),
            70
        ));
    }

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
                .filter(r -> dni.equals(r.getDniPasajero()))
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
        if (dto.getDniPasajero() == null || dto.getDniPasajero().trim().isEmpty()) {
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
        nuevaReserva.setFechaSalida(vuelo.getFechaSalida());
        nuevaReserva.setFechaLlegada(vuelo.getFechaLlegada());
        nuevaReserva.setNombrePasajero(dto.getNombrePasajero());
        nuevaReserva.setDniPasajero(dto.getDniPasajero());
        nuevaReserva.setAsiento(generarAsiento());
        nuevaReserva.setPrecio(vuelo.getPrecioBase());
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

    private static class Vuelo {
        private final String numero;
        private final String origen;
        private final String destino;
        private final LocalDateTime fechaSalida;
        private final LocalDateTime fechaLlegada;
        private int asientosDisponibles;
        private final double precioBase;

        Vuelo(String numero, String origen, String destino, LocalDateTime salida,
              LocalDateTime llegada, int asientosDisponibles) {
            this.numero = numero;
            this.origen = origen;
            this.destino = destino;
            this.fechaSalida = salida;
            this.fechaLlegada = llegada;
            this.asientosDisponibles = asientosDisponibles;
            this.precioBase = calcularPrecio(salida, destino);
        }

        private double calcularPrecio(LocalDateTime fecha, String destino) {
            double base = 75.0;
            if ("BCN".equals(destino) || "MAD".equals(destino)) {
                base += 50;
            }
            if (fecha.getHour() < 6 || fecha.getHour() > 22) {
                base *= 1.2;
            }
            return base;
        }

        public String getNumero() { return numero; }
        public String getOrigen() { return origen; }
        public String getDestino() { return destino; }
        public LocalDateTime getFechaSalida() { return fechaSalida; }
        public LocalDateTime getFechaLlegada() { return fechaLlegada; }
        public int getAsientosDisponibles() { return asientosDisponibles; }
        public double getPrecioBase() { return precioBase; }

        public void reservarAsiento() {
            asientosDisponibles--;
        }

        public void liberarAsiento() {
            asientosDisponibles++;
        }
    }
}