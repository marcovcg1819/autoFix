package cl.usach.ms_catalogosv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosv.entities.TiposEntity;
import cl.usach.ms_catalogosv.repositories.TiposRepository;

@Service
public class TiposService {
	@Autowired
	private TiposRepository tiposRepository;
	
	public List<TiposEntity> getAllTipos(){
		return tiposRepository.findAll();
	}
	
	public TiposEntity getTipoById(Long id){
		return tiposRepository.findById(id).get();
	}
	
	public TiposEntity saveTipo(TiposEntity tipo){
		return tiposRepository.save(tipo);
	}
	
	public TiposEntity updateTipo(Long id , TiposEntity tipo){
		TiposEntity tip = tiposRepository.findById(id).get();
		
		tip.setTipo(tipo.getTipo());
		
		tiposRepository.save(tip);
		return tip;
	}
	
	public String removeTipo(Long id){
		TiposEntity t = new TiposEntity();
		t.setId(id);
		tiposRepository.delete(t);
		return "OK";
	}
}
