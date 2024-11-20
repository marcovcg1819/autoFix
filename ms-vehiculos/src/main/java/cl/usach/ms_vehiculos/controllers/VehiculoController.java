package cl.usach.ms_vehiculos.controllers;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.ms_vehiculos.entities.VehiculoEntity;
import cl.usach.ms_vehiculos.modelos.ResponseListVeviculos;
import cl.usach.ms_vehiculos.services.VehiculoService;

@RestController
//@RequestMapping("/vehiculo")
@CrossOrigin("*")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;

	@GetMapping("/vehiculo")
	public ResponseEntity<Collection<ResponseListVeviculos>> getAllVehiculos() {
		Collection<ResponseListVeviculos> vehiculoList = vehiculoService.getAllVehiculos();
		if (vehiculoList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(vehiculoList);
	}
	
	@GetMapping("/vehiculo/bypatente/{patente}")
	public ResponseEntity<ResponseListVeviculos> getAllVehiculos(@PathVariable("patente") String patente) {
		ResponseListVeviculos veh = vehiculoService.getByPatente(patente);
		if (veh == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(veh);
	}

	@GetMapping("/vehiculo/{id}")
	public ResponseEntity<VehiculoEntity> getVehiculoById(@PathVariable("id") Long id) {
		VehiculoEntity veh = vehiculoService.getVehiculoById(id);
		if (veh == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(veh);
	}

	@PostMapping("/vehiculo")
	public ResponseEntity<VehiculoEntity> saveVehiculo(@RequestBody VehiculoEntity vehiculo) {
		VehiculoEntity veh = vehiculoService.saveVehiculo(vehiculo);
		if (veh == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(veh);
	}
	
	@PutMapping("/vehiculo")
	public ResponseEntity<VehiculoEntity> updateVehiculo(@RequestBody VehiculoEntity vehiculo) {
		VehiculoEntity veh = vehiculoService.updateVehiculo(vehiculo.getId(), vehiculo);
		if (veh == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(veh);
	}
	
	@DeleteMapping("/vehiculo/{id}")
	public ResponseEntity<String> deleteVehiculo(@PathVariable("id") Long id) {
		try {
			vehiculoService.removeVehiculo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}


}
