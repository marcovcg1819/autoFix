package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.RecargoKilometrajeEntity;
import cl.usach.ms_catalogosr.repositories.RecargoKilometrajeRepository;

@Service
public class RecargoKilometrajeService {
	
	@Autowired
	private RecargoKilometrajeRepository recargoKilometrajeRepository;
	
	public List<RecargoKilometrajeEntity> getAllRecargos(){
		return recargoKilometrajeRepository.findAll();
	}
	
	public RecargoKilometrajeEntity getRecargoById(Long id){
		return recargoKilometrajeRepository.findById(id).get();
	}
	
	public RecargoKilometrajeEntity saveRecargo(RecargoKilometrajeEntity recargo){
		return recargoKilometrajeRepository.save(recargo);
	}
	
	public RecargoKilometrajeEntity updateRecargo(Long id , RecargoKilometrajeEntity recargo){
		RecargoKilometrajeEntity rec = recargoKilometrajeRepository.findById(id).get();
		rec.setDescuento_porcentaje(recargo.getDescuento_porcentaje());
		rec.setId_modelo(recargo.getId_modelo());
		rec.setIntervalo(recargo.getIntervalo());
		
		recargoKilometrajeRepository.save(rec);
		return rec;
	}
	
	public String removeRecargo(Long id){
		RecargoKilometrajeEntity r = new RecargoKilometrajeEntity();
		r.setId(id);
//		recargoKilometrajeRepository.delete(r);
		return "OK";
	}
}
