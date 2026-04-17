package com.gastro.aerolinea.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gastro.aerolinea.avion.Avion;
import com.gastro.aerolinea.avion.AvionRepository;
import com.gastro.aerolinea.pasajero.Pasajero;
import com.gastro.aerolinea.pasajero.PasajeroRepository;
import com.gastro.aerolinea.vuelo.EstadoVuelo;
import com.gastro.aerolinea.vuelo.Vuelo;
import com.gastro.aerolinea.vuelo.VueloRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            AvionRepository avionRepository,
            VueloRepository vueloRepository,
            PasajeroRepository pasajeroRepository) {
        return args -> {
            if (avionRepository.count() == 0) {
                Avion avion1 = Avion.builder()
                        .matricula("EC-1234")
                        .modelo(com.gastro.aerolinea.avion.ModeloAvion.BOEING_737)
                        .fabricante("Boeing")
                        .capacidadPasajeros(180)
                        .anioFabricacion(2020)
                        .build();

                Avion avion2 = Avion.builder()
                        .matricula("EC-5678")
                        .modelo(com.gastro.aerolinea.avion.ModeloAvion.AIRBUS_A320)
                        .fabricante("Airbus")
                        .capacidadPasajeros(150)
                        .anioFabricacion(2019)
                        .build();

                Avion avion3 = Avion.builder()
                        .matricula("EC-9012")
                        .modelo(com.gastro.aerolinea.avion.ModeloAvion.BOEING_777)
                        .fabricante("Boeing")
                        .capacidadPasajeros(300)
                        .anioFabricacion(2021)
                        .build();

                avionRepository.save(avion1);
                avionRepository.save(avion2);
                avionRepository.save(avion3);

                Vuelo vuelo1 = Vuelo.builder()
                        .origen("MAD")
                        .destino("BCN")
                        .horaSalida(LocalDateTime.now().plusDays(1))
                        .horaLlegada(LocalDateTime.now().plusDays(1).plusHours(2))
                        .precio(120.0)
                        .estado(EstadoVuelo.PROGRAMADO)
                        .avion(avion1)
                        .build();

                Vuelo vuelo2 = Vuelo.builder()
                        .origen("BCN")
                        .destino("MAD")
                        .horaSalida(LocalDateTime.now().plusDays(2))
                        .horaLlegada(LocalDateTime.now().plusDays(2).plusHours(2))
                        .precio(120.0)
                        .estado(EstadoVuelo.PROGRAMADO)
                        .avion(avion1)
                        .build();

                Vuelo vuelo3 = Vuelo.builder()
                        .origen("MAD")
                        .destino("JFK")
                        .horaSalida(LocalDateTime.now().plusDays(3))
                        .horaLlegada(LocalDateTime.now().plusDays(3).plusHours(8))
                        .precio(450.0)
                        .estado(EstadoVuelo.PROGRAMADO)
                        .avion(avion2)
                        .build();

                Vuelo vuelo4 = Vuelo.builder()
                        .origen("MAD")
                        .destino("LON")
                        .horaSalida(LocalDateTime.now().plusDays(1).plusHours(4))
                        .horaLlegada(LocalDateTime.now().plusDays(1).plusHours(6))
                        .precio(180.0)
                        .estado(EstadoVuelo.DISPONIBLE)
                        .avion(avion3)
                        .build();

                Vuelo vuelo5 = Vuelo.builder()
                        .origen("BCN")
                        .destino("PAR")
                        .horaSalida(LocalDateTime.now().plusDays(5))
                        .horaLlegada(LocalDateTime.now().plusDays(5).plusHours(2))
                        .precio(90.0)
                        .estado(EstadoVuelo.PROGRAMADO)
                        .avion(avion2)
                        .build();

                vueloRepository.save(vuelo1);
                vueloRepository.save(vuelo2);
                vueloRepository.save(vuelo3);
                vueloRepository.save(vuelo4);
                vueloRepository.save(vuelo5);

                Pasajero pasajero1 = Pasajero.builder()
                        .dni("12345678A")
                        .nombre("Juan")
                        .apellidos("García López")
                        .email("juan.garcia@email.com")
                        .telefono("+34612345678")
                        .fechaNacimiento(java.time.LocalDate.of(1985, 5, 15))
                        .nacionalidad("Española")
                        .build();

                Pasajero pasajero2 = Pasajero.builder()
                        .dni("87654321B")
                        .nombre("María")
                        .apellidos("Rodríguez Sánchez")
                        .email("maria.rodriguez@email.com")
                        .telefono("+34723456789")
                        .fechaNacimiento(java.time.LocalDate.of(1990, 8, 22))
                        .nacionalidad("Española")
                        .build();

                Pasajero pasajero3 = Pasajero.builder()
                        .dni("X1234567C")
                        .nombre("John")
                        .apellidos("Smith Doe")
                        .email("john.smith@email.com")
                        .telefono("+441234567890")
                        .fechaNacimiento(java.time.LocalDate.of(1978, 3, 10))
                        .nacionalidad("Británica")
                        .build();

                Pasajero pasajero4 = Pasajero.builder()
                        .dni("Y9876543D")
                        .nombre("Laura")
                        .apellidos("Martínez Torres")
                        .email("laura.martinez@email.com")
                        .telefono("+34834567890")
                        .fechaNacimiento(java.time.LocalDate.of(1995, 12, 1))
                        .nacionalidad("Española")
                        .build();

                Pasajero pasajero5 = Pasajero.builder()
                        .dni("11223344E")
                        .nombre("Carlos")
                        .apellidos("Fernández Gómez")
                        .email("carlos.fernandez@email.com")
                        .telefono("+34945678901")
                        .fechaNacimiento(java.time.LocalDate.of(1982, 7, 28))
                        .nacionalidad("Española")
                        .build();

                pasajeroRepository.save(pasajero1);
                pasajeroRepository.save(pasajero2);
                pasajeroRepository.save(pasajero3);
                pasajeroRepository.save(pasajero4);
                pasajeroRepository.save(pasajero5);
            }
        };
    }
}