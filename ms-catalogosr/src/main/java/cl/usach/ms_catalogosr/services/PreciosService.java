package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.DescuentosPorBonosEntity;
import cl.usach.ms_catalogosr.entities.PreciosEntity;
import cl.usach.ms_catalogosr.repositories.PreciosRepository;

@Service
public class PreciosService {
	
	@Autowired
	private PreciosRepository preciosRepository;
	
	public List<PreciosEntity> getAllPrecios(){
		return preciosRepository.findAll();
	}
	
	public PreciosEntity getPrecioById(Long id){
		return preciosRepository.findById(id).get();
	}
	
	public PreciosEntity savePrecio(PreciosEntity bono){
		return preciosRepository.save(bono);
	}
	
	public PreciosEntity updatePrecio(Long id , PreciosEntity precio){
		PreciosEntity pre = preciosRepository.findById(id).get();
		pre.setId_tipo_reparacion(precio.getId_tipo_reparacion());
		pre.setId_tipo_vehiculo(precio.getId_tipo_vehiculo());
		pre.setPrecio(precio.getPrecio());
		
		preciosRepository.save(pre);
		return pre;
	}
	
	public String removePrecio(Long id){
		PreciosEntity p = new PreciosEntity();
		p.setId(id);
		preciosRepository.delete(p);
		return "OK";
	}

}
