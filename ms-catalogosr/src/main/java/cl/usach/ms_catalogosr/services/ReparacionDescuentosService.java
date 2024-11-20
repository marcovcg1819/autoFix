package cl.usach.ms_catalogosr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_catalogosr.entities.ReparacionDescuentosEntity;
import cl.usach.ms_catalogosr.repositories.ReparacionDescuentosRepository;

@Service
public class ReparacionDescuentosService{

	@Autowired
	private ReparacionDescuentosRepository reparacionDescuentosRepository;
	
	public List<ReparacionDescuentosEntity> getAllDescuentos(){
		return reparacionDescuentosRepository.findAll();
	}
	
	public ReparacionDescuentosEntity getDescuentoById(Long id){
		return reparacionDescuentosRepository.findById(id).get();
	}
	
	public ReparacionDescuentosEntity saveDescuento(ReparacionDescuentosEntity descuento){
		return reparacionDescuentosRepository.save(descuento);
	}
	
	public ReparacionDescuentosEntity updateDescuento(Long id , ReparacionDescuentosEntity descuento){
		ReparacionDescuentosEntity desc = reparacionDescuentosRepository.findById(id).get();
		desc.setDescuento_porcentaje(descuento.getDescuento_porcentaje());
		desc.setId_tipo_vehiculo(descuento.getId_tipo_vehiculo());
		desc.setNumero_reparaciones_ultimo_anio(descuento.getNumero_reparaciones_ultimo_anio());
		
		reparacionDescuentosRepository.save(desc);
		return desc;
	}
	
	public String removeDescuento(Long id){
		ReparacionDescuentosEntity d = new ReparacionDescuentosEntity();
		d.setId(id);
//		reparacionDescuentosRepository.delete(r);
		return "OK";
	}
}
