package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.PreciosEntity;
import cl.usach.ms_catalogosr.entities.RecargoAntiguedadEntity;
import cl.usach.ms_catalogosr.repositories.PreciosRepository;
import cl.usach.ms_catalogosr.repositories.RecargoAntiguedadRepository;

@Service
public class RecargoAntiguedadService {

	@Autowired
	private RecargoAntiguedadRepository recargoAntiguedadRepository;
	
	public List<RecargoAntiguedadEntity> getAllRecargos(){
		return recargoAntiguedadRepository.findAll();
	}
	
	public RecargoAntiguedadEntity getRecargoById(Long id){
		return recargoAntiguedadRepository.findById(id).get();
	}
	
	public RecargoAntiguedadEntity saveRecargo(RecargoAntiguedadEntity recargo){
		return recargoAntiguedadRepository.save(recargo);
	}
	
	public RecargoAntiguedadEntity updateRecargo(Long id , RecargoAntiguedadEntity recargo){
		RecargoAntiguedadEntity rec = recargoAntiguedadRepository.findById(id).get();
		rec.setDescuento_porcentaje(recargo.getDescuento_porcentaje());
		rec.setId_modelo(recargo.getId_modelo());
		rec.setIntervalo_anios(recargo.getIntervalo_anios());
		
		recargoAntiguedadRepository.save(rec);
		return rec;
	}
	
	public String removeRecargo(Long id){
		RecargoAntiguedadEntity r = new RecargoAntiguedadEntity();
		r.setId(id);
//		recargoAntiguedadRepository.delete(r);
		return "OK";
	}
}
