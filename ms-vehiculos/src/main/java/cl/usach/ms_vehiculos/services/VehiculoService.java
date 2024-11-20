package cl.usach.ms_vehiculos.services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_vehiculos.entities.VehiculoEntity;
import cl.usach.ms_vehiculos.modelos.ResponseListVeviculos;
import cl.usach.ms_vehiculos.repositories.VehiculoRepository;

@Service
public class VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	public Collection<ResponseListVeviculos> getAllVehiculos(){
		return vehiculoRepository.getVehiculoList();
	}
	
	public ResponseListVeviculos getByPatente(String patente){
		return vehiculoRepository.getVehiculoByPatente(patente);
	}
	
	public VehiculoEntity getVehiculoById(Long id){
		return vehiculoRepository.findById(id).get();
	}
	
	public VehiculoEntity saveVehiculo(VehiculoEntity vehiculo){
		return vehiculoRepository.save(vehiculo);
	}
	
	public VehiculoEntity updateVehiculo(Long id , VehiculoEntity vehiculo){
		VehiculoEntity veh = vehiculoRepository.findById(id).get();
		
		veh.setAnio_fabricacion(vehiculo.getAnio_fabricacion());
		veh.setId_marca(vehiculo.getId_marca());
		veh.setId_modelo(vehiculo.getId_modelo());
		veh.setId_tipo_auto(vehiculo.getId_tipo_auto());
		veh.setKilometraje(vehiculo.getKilometraje());
		veh.setN_asientos(vehiculo.getN_asientos());
		veh.setN_patente(vehiculo.getN_patente());
		
		vehiculoRepository.save(veh);
		return veh;
	}
	
	public String removeVehiculo(Long id){
		VehiculoEntity v = new VehiculoEntity();
		v.setId(id);
		vehiculoRepository.delete(v);
		return "OK";
	}
}
