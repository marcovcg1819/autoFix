package cl.usach.ms_catalogosr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.usach.ms_catalogosr.entities.ReparacionDescuentosEntity;
import cl.usach.ms_catalogosr.services.ReparacionDescuentosService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class ReparacionDescuentosController {
	
	@Autowired
	private ReparacionDescuentosService reparacionDescuentosService;

	@GetMapping("/reparaciondescuentos")
	public ResponseEntity<List<ReparacionDescuentosEntity>> getAllDescuentos() {
		List<ReparacionDescuentosEntity> descuentosList = reparacionDescuentosService.getAllDescuentos();
		if (descuentosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(descuentosList);
	}

	@GetMapping("/reparaciondescuentos/{id}")
	public ResponseEntity<ReparacionDescuentosEntity> getReparacionById(@PathVariable("id") Long id) {
		ReparacionDescuentosEntity desc = reparacionDescuentosService.getDescuentoById(id);
		if (desc == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(desc);
	}

	@PostMapping("/reparaciondescuentos")
	public ResponseEntity<ReparacionDescuentosEntity> saveReparacion(@RequestBody ReparacionDescuentosEntity reparacion) {
		ReparacionDescuentosEntity desc = reparacionDescuentosService.saveDescuento(reparacion);
		if (desc == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(desc);
	}
	
	@PutMapping("/reparaciondescuentos")
	public ResponseEntity<ReparacionDescuentosEntity> updateDescuento(@RequestBody ReparacionDescuentosEntity reparacion) {
		ReparacionDescuentosEntity desc = reparacionDescuentosService.updateDescuento(reparacion.getId(), reparacion);
		if (desc == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(desc);
	}
	
	@DeleteMapping("/reparaciondescuentos/{id}")
	public ResponseEntity<String> deleteDescuento(@PathVariable("id") Long id) {
		try {
			reparacionDescuentosService.removeDescuento(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
