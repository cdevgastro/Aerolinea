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
}