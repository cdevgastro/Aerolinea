package com.gastro.aerolinea.vuelo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VueloController.class)
class VueloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private VueloService vueloService;

    @Autowired
    private ObjectMapper objectMapper;

    private VueloDTO vueloDTO;

    @BeforeEach
    void setUp() {
        vueloDTO = VueloDTO.builder()
                .numeroVuelo(101L)
                .origen("MAD")
                .destino("BCN")
                .horaSalida(LocalDateTime.now().plusDays(1))
                .horaLlegada(LocalDateTime.now().plusDays(1).plusHours(2))
                .precio(100.0)
                .estado(EstadoVuelo.PROGRAMADO)
                .build();
    }

    @Test
    void listarTodos_ShouldReturnList() throws Exception {
        List<VueloDTO> vuelos = Arrays.asList(vueloDTO);
        when(vueloService.buscarTodos()).thenReturn(vuelos);

        mockMvc.perform(get("/api/vuelo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].numeroVuelo").value(101));
    }

    @Test
    void listarDisponibles_ShouldReturnList() throws Exception {
        List<VueloDTO> vuelos = Arrays.asList(vueloDTO);
        when(vueloService.buscarDisponibles()).thenReturn(vuelos);

        mockMvc.perform(get("/api/vuelo/disponibles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroVuelo").value(101));
    }

    @Test
    void buscarOrigenDestino_ShouldReturnList() throws Exception {
        List<VueloDTO> vuelos = Arrays.asList(vueloDTO);
        when(vueloService.buscarPorOrigenDestino("MAD", "BCN")).thenReturn(vuelos);

        mockMvc.perform(get("/api/vuelo/buscar")
                .param("origen", "MAD")
                .param("destino", "BCN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroVuelo").value(101));
    }

    @Test
    void crear_ShouldReturnCreatedVuelo() throws Exception {
        when(vueloService.crear(any(VueloDTO.class))).thenReturn(vueloDTO);

        mockMvc.perform(post("/api/vuelo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vueloDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroVuelo").value(101));
    }

    @Test
    void actualizarEstado_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(put("/api/vuelo/101/estado")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"PROGRAMADO\""))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminar_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/vuelo/101/cancelar"))
                .andExpect(status().isNoContent());
    }
}
