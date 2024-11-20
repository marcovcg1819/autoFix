package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.DescuentosPorBonosEntity;
import cl.usach.ms_catalogosr.repositories.DescuentosPorBonosRepository;

@Service
public class DescuentosPorBonosService {
	
	@Autowired
	private DescuentosPorBonosRepository descuentosPorBonosRepository;
	
	public List<DescuentosPorBonosEntity> getAllBonos(){
		return descuentosPorBonosRepository.findAll();
	}
	
	public DescuentosPorBonosEntity getBonoById(Long id){
		return descuentosPorBonosRepository.findById(id).get();
	}
	
	public DescuentosPorBonosEntity saveBono(DescuentosPorBonosEntity bono){
		return descuentosPorBonosRepository.save(bono);
	}
	
	public DescuentosPorBonosEntity updateBono(Long id , DescuentosPorBonosEntity bono){
		DescuentosPorBonosEntity bon = descuentosPorBonosRepository.findById(id).get();
		
		bon.setDinero_bono(bono.getDinero_bono());
		bon.setMarca(bono.getMarca());
		bon.setNumero_bono(bono.getNumero_bono());
		
		descuentosPorBonosRepository.save(bon);
		return bon;
	}
	
	public String removeBono(Long id){
		DescuentosPorBonosEntity b = new DescuentosPorBonosEntity();
		b.setId(id);
		descuentosPorBonosRepository.delete(b);
		return "OK";
	}

}
