package com.gastro.aerolinea.reserva;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gastro.aerolinea.vuelo.Vuelo;
import com.gastro.aerolinea.vuelo.VueloRepository;
import com.gastro.aerolinea.vuelo.VueloService;
@Service
public class ReservaServiceImpl implements ReservaService {

    private final List<ReservaDTO> reservas = new ArrayList<>();
    private final Map<String, Vuelo> vuelos = new HashMap<>();
    private long nextId = 1;
    private static final Random random = new Random();

    @Autowired
    private VueloService vueloService;
    @Autowired
    private ReservaRepository reservarepository;
    @Autowired
    private ReservaMapper mapper;
    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public ReservaDTO crearReserva(ReservaDTO dto) {
        validarReserva(dto); // revisar
        Reserva nuevaReserva = mapper.toEntity(dto);
        nuevaReserva = reservarepository.save(nuevaReserva);
        vueloService.reservarAsiento(nuevaReserva.getVuelo().getNumeroVuelo());
        return mapper.toDto(nuevaReserva);
    }


    @Override
    public List<ReservaDTO> listarPorPasajero(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("DNI requerido");
        }   

        List<Reserva> reservasEntity = reservarepository.findByPasajeroDni(dni);

        return mapper.toDtoList(reservasEntity);
    }


    @Override
    public List<ReservaDTO> listarPorVuelo(Long id) {
        if (id == null || id.toString().trim().isEmpty()) {
            throw new IllegalArgumentException("ID de vuelo requerido");
        }

        Optional<Vuelo> reservas = reservarepository.findByVueloNumeroVuelo(id);

        return mapper.toDtoList(reservas);
    }



    @Override
    public void cancelarReserva(String localizador) {
        if (localizador == null || localizador.trim().isEmpty()) {
            throw new IllegalArgumentException("Localizador requerido");
        }

        Reserva reserva = reservarepository.finByLocalizador(localizador);

        if (reserva == null) {
            throw new IllegalArgumentException("Reserva no encontrada: " + localizador);
        }

        reserva.setEstado("CANCELADA");
        reservarepository.save(reserva); 

    }

    private void validarReserva(ReservaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Reserva no puede ser nula");
        }
        if (dto.getPasajero().getDni() == null || dto.getPasajero().getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("DNI del pasajero es obligatorio");
        }
        if (dto.getPasajero().getNombre() == null || dto.getPasajero().getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre del pasajero es obligatorio");
        }
        if (dto.getVuelo().getNumeroVuelo() == null || dto.getVuelo().getNumeroVuelo().toString().trim().isEmpty()) {
            throw new IllegalArgumentException("Número de vuelo es obligatorio");
        }
    }

    private void verificarDisponibilidad(Vuelo vuelo) {
        if (vuelo.getAsientosDisponibles() <= 0) {
            throw new IllegalStateException("No hay asientos disponibles en este vuelo");
        }
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