package cl.usach.ms_catalogosv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosv.entities.MarcasEntity;
import cl.usach.ms_catalogosv.repositories.MarcasRepository;

@Service
public class MarcasService {
	@Autowired
	private MarcasRepository marcasRepository;
	
	public List<MarcasEntity> getAllMarcas(){
		return marcasRepository.findAll();
	}
	
	public MarcasEntity getMarcaById(Long id){
		return marcasRepository.findById(id).get();
	}
	
	public MarcasEntity saveMarca(MarcasEntity marca){
		return marcasRepository.save(marca);
	}
	
	public MarcasEntity updateMarca(Long id , MarcasEntity marca){
		MarcasEntity mar = marcasRepository.findById(id).get();
		
		mar.setMarca(marca.getMarca());
		
		marcasRepository.save(mar);
		return mar;
	}
	
	public String removeMarca(Long id){
		MarcasEntity m = new MarcasEntity();
		m.setId(id);
		marcasRepository.delete(m);
		return "OK";
	}
}
