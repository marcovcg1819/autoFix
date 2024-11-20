package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.TipoReparacionesEntity;
import cl.usach.ms_catalogosr.repositories.TipoReparacionesRepository;

@Service
public class TipoReparacionesService {

	@Autowired
	private TipoReparacionesRepository tipoReparacionesRepository;
	
	public List<TipoReparacionesEntity> getAllTipos(){
		return tipoReparacionesRepository.findAll();
	}
	
	public TipoReparacionesEntity getTipoById(Long id){
		return tipoReparacionesRepository.findById(id).get();
	}
	
	public TipoReparacionesEntity saveTipo(TipoReparacionesEntity tipo){
		return tipoReparacionesRepository.save(tipo);
	}
	
	public TipoReparacionesEntity updateTipo(Long id , TipoReparacionesEntity tipo){
		TipoReparacionesEntity tip = tipoReparacionesRepository.findById(id).get();
		tip.setTipo(tipo.getTipo());
		
		tipoReparacionesRepository.save(tip);
		return tip;
	}
	
	public String removeTipo(Long id){
		TipoReparacionesEntity t = new TipoReparacionesEntity();
		t.setId(id);
//		tipoReparacionesRepository.delete(t);
		return "OK";
	}
}
