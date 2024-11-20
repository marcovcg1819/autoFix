package cl.usach.ms_catalogosv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosv.entities.ModelosEntity;
import cl.usach.ms_catalogosv.repositories.ModelosRepository;

@Service
public class ModelosService {
	@Autowired
	private ModelosRepository modelosRepository;
	
	public List<ModelosEntity> getAllModelos(){
		return modelosRepository.findAll();
	}
	
	public ModelosEntity getModeloById(Long id){
		return modelosRepository.findById(id).get();
	}
	
	public ModelosEntity saveModelo(ModelosEntity modelo){
		return modelosRepository.save(modelo);
	}
	
	public ModelosEntity updateModelo(Long id , ModelosEntity modelo){
		ModelosEntity mod = modelosRepository.findById(id).get();
		
		mod.setModelos(modelo.getModelos());
		
		modelosRepository.save(mod);
		return mod;
	}
	
	public String removeModelo(Long id){
		ModelosEntity m = new ModelosEntity();
		m.setId(id);
		modelosRepository.delete(m);
		return "OK";
	}
}
