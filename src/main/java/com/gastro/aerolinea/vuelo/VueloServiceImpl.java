package com.gastro.aerolinea.vuelo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gastro.aerolinea.avion.Avion;
import com.gastro.aerolinea.avion.AvionDTO;
import com.gastro.aerolinea.avion.AvionMapper;

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
	public Optional<Vuelo> findByNumeroVuelo(Long numeroVuelo) {
	    return vueloRepository.findByNumeroVuelo(numeroVuelo);
	}

	@Override
	public void cambiarEstado(Long numeroVuelo, EstadoVuelo estado) {
	    Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
	            .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con numero: " + numeroVuelo));

	    vuelo.setEstado(estado);
	    vueloRepository.save(vuelo);
	}
	
	@Override
    public void eliminar(Long numeroVuelo) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con numero: " + numeroVuelo));

        vueloRepository.delete(vuelo);
    }
    
	@Override
    public Vuelo crear(Vuelo vuelo) {
        vueloRepository.findByNumeroVuelo(vuelo.getNumeroVuelo())
                .ifPresent(a -> {
                    throw new RuntimeException("Ya existe un vuelo con numero: " + vuelo.getNumeroVuelo());
                });

        vueloRepository.save(vuelo);
        return vuelo;
    }
}