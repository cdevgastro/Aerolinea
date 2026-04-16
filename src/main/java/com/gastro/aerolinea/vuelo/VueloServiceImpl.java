package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VueloServiceImpl implements VueloService {

	private final VueloRepository vueloRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Vuelo> buscarTodos() {
		return vueloRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vuelo> buscarPorOrigenDestino(String origen, String destino) {
		return vueloRepository.findByOrigenAndDestino(origen, destino);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Vuelo> buscarDisponibles() {
		return vueloRepository.buscarDisponibles();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vuelo> findByNumeroVuelo(String numeroVuelo) {
	    return vueloRepository.findByNumeroVuelo(numeroVuelo);
	}

	@Override
	public void cambiarEstado(String numeroVuelo, EstadoVuelo estado) {
	    Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
	            .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con numero: " + numeroVuelo));

	    vuelo.setEstado(estado);
	    vueloRepository.save(vuelo);
	}
	
    public void eliminar(String numeroVuelo) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con numero: " + numeroVuelo));

        vueloRepository.delete(vuelo);
    }
}