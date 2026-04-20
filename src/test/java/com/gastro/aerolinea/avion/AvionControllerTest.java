package com.gastro.aerolinea.avion;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AvionController.class)
class AvionControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private AvionService avionService;

    @Autowired
    private ObjectMapper objectMapper;

    private AvionDTO avionDTO;

    @BeforeEach
    void setUp() {
        avionDTO = new AvionDTO();
        avionDTO.setId(1L);
        avionDTO.setMatricula("EC-1234");
        avionDTO.setModelo("Boeing 737");
        avionDTO.setFabricante("Boeing");
        avionDTO.setCapacidadPasajeros(180);
        avionDTO.setAnioFabricacion(2020);
    }

    @Test
    void listarTodos_ShouldReturnList() throws Exception {
        List<AvionDTO> aviones = Arrays.asList(avionDTO);
        when(avionService.obtenerTodos()).thenReturn(aviones);

        mockMvc.perform(get("/api/aviones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].matricula").value("EC-1234"));
    }

    @Test
    void obtenerPorMatricula_ShouldReturnAvion() throws Exception {
        when(avionService.obtenerPorMatricula("EC-1234")).thenReturn(avionDTO);

        mockMvc.perform(get("/api/aviones/EC-1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value("EC-1234"));
    }

    @Test
    void crear_ShouldReturnCreatedAvion() throws Exception {
        when(avionService.crear(any(AvionDTO.class))).thenReturn(avionDTO);

        mockMvc.perform(post("/api/aviones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.matricula").value("EC-1234"));
    }

    @Test
    void actualizar_ShouldReturnUpdatedAvion() throws Exception {
        when(avionService.actualizar(eq("EC-1234"), any(AvionDTO.class))).thenReturn(avionDTO);

        mockMvc.perform(put("/api/aviones/EC-1234")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(avionDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value("EC-1234"));
    }

    @Test
    void eliminar_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/aviones/EC-1234"))
                .andExpect(status().isNoContent());
    }
}
