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

    // Usamos CopyOnWriteArrayList para que sea seguro en un entorno web (multi-hilo)
    private final List<Reserva> reservasDatabase = new CopyOnWriteArrayList<>();

    @Override
    public ReservaDTO crearReserva(ReservaDTO dto) {
        // Generamos un ID único para la reserva
        String localizador = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Mapeo de DTO a Entidad
        Reserva nuevaReserva = Reserva.builder()
                .idReserva(localizador)
                .dniPasajero(dto.getPasajeroDni())
                .numeroVuelo(dto.getNumeroVuelo())
                .estado("CONFIRMADA")
                .build();

        reservasDatabase.add(nuevaReserva);

        // Sincronizamos el DTO con el localizador generado para devolverlo
        dto.setLocalizador(localizador);
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> listarPorPasajero(String dni) {
        return reservasDatabase.stream()
                .filter(r -> r.getDniPasajero().equalsIgnoreCase(dni))
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> listarPorVuelo(String idVuelo) {
        return reservasDatabase.stream()
                .filter(r -> r.getNumeroVuelo().equalsIgnoreCase(idVuelo))
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelarReserva(String localizador) {
        reservasDatabase.removeIf(r -> r.getIdReserva().equalsIgnoreCase(localizador));
    }

    /**
     * Mapeador interno de Entidad a DTO
     */
    private ReservaDTO convertirADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setLocalizador(reserva.getIdReserva());
        dto.setPasajeroDni(reserva.getDniPasajero());
        dto.setNumeroVuelo(reserva.getNumeroVuelo());
        return dto;
    }
}