package com.gastro.aerolinea.avion;

import java.util.List;

public interface AvionService {

    List<AvionDTO> obtenerTodos();

    AvionDTO obtenerPorMatricula(String matricula);

    AvionDTO crear(AvionDTO avionDTO);

    AvionDTO actualizar(String matricula, AvionDTO avionDTO);

    void eliminar(String matricula);
}
