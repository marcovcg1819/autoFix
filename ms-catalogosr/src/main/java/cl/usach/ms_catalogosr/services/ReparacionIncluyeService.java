package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.ReparacionIncluyeEntity;
import cl.usach.ms_catalogosr.repositories.ReparacionIncluyeRepository;

@Service
public class ReparacionIncluyeService {
	@Autowired
	private ReparacionIncluyeRepository reparacionIncluyeRepository;
	
	public List<ReparacionIncluyeEntity> getAllRIncluye(){
		return reparacionIncluyeRepository.findAll();
	}
	
	public ReparacionIncluyeEntity getRIncluyeById(Long id){
		return reparacionIncluyeRepository.findById(id).get();
	}
	
	public ReparacionIncluyeEntity saveRIncluye(ReparacionIncluyeEntity reparacion){
		return reparacionIncluyeRepository.save(reparacion);
	}
	
	public ReparacionIncluyeEntity updateRIncluye(Long id , ReparacionIncluyeEntity reparacion){
		ReparacionIncluyeEntity rep = reparacionIncluyeRepository.findById(id).get();
		rep.setId_tipo_reparacion(reparacion.getId_tipo_reparacion());
		rep.setReparacion(reparacion.getReparacion());
		
		reparacionIncluyeRepository.save(rep);
		return rep;
	}
	
	public String removeRIncluye(Long id){
		ReparacionIncluyeEntity r = new ReparacionIncluyeEntity();
		r.setId(id);
//		reparacionIncluyeRepository.delete(r);
		return "OK";
	}
}
