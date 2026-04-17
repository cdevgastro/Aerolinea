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
    private final VueloMapper vueloMapper;

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarTodos() {
        return vueloRepository.findAll()
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarPorOrigenDestino(String origen, String destino) {
        return vueloRepository.findByOrigenAndDestino(origen, destino)
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VueloDTO> buscarDisponibles() {
        return vueloRepository.buscarDisponibles()
                .stream()
                .map(vueloMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VueloDTO> findByNumeroVuelo(Long numeroVuelo) {
        return vueloRepository.findByNumeroVuelo(numeroVuelo)
                .map(vueloMapper::toDTO);
    }

    @Override
    public void cambiarEstado(Long numeroVuelo, EstadoVuelo estado) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        vuelo.setEstado(estado);
        vueloRepository.save(vuelo);
    }

    @Override
    public void eliminar(Long numeroVuelo) {
        Vuelo vuelo = vueloRepository.findByNumeroVuelo(numeroVuelo)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        vueloRepository.delete(vuelo);
    }

    @Override
    public VueloDTO crear(VueloDTO vueloDTO) {
        Vuelo vuelo = vueloMapper.toEntity(vueloDTO);

        vueloRepository.save(vuelo);

        return vueloMapper.toDTO(vuelo);
    }
	private String avionDisponible(Vuelo vuelo) {
		if(vuelo.getHoraLlegada().isBefore(vuelo.getHoraSalida())){
			return "la fecha de llegada debe ser mayor que la de salida";
		}
    	for(Vuelo vueloOficial:vueloRepository.findByAvion(vuelo.getAvion())){
			if (seSolapa(vueloOficial, vuelo)){
				return "Se solapan fechas el avion: "+ vuelo.getAvion().getMatricula() +", tiene fechas entre "+ vueloOficial.getHoraSalida() + " y " + vueloOficial.getHoraLlegada() + " ocupadas.";
			}
		}
    	return "ok";
	}	
	private boolean seSolapa(Vuelo vueloOficial, Vuelo vuelo) {
		return (vueloOficial.getHoraSalida().isBefore(vuelo.getHoraLlegada()) && vueloOficial.getHoraLlegada().isAfter(vuelo.getHoraSalida()));
	}

}