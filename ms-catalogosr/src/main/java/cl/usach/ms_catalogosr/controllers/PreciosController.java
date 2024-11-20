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

import cl.usach.ms_catalogosr.entities.PreciosEntity;
import cl.usach.ms_catalogosr.services.PreciosService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class PreciosController {

	@Autowired
	private PreciosService preciosService;

	@GetMapping("/precios")
	public ResponseEntity<List<PreciosEntity>> getAllPrecios() {
		List<PreciosEntity> preciosList = preciosService.getAllPrecios();
		if (preciosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(preciosList);
	}

	@GetMapping("/precios/{id}")
	public ResponseEntity<PreciosEntity> getPrecioById(@PathVariable("id") Long id) {
		PreciosEntity pre = preciosService.getPrecioById(id);
		if (pre == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pre);
	}

	@PostMapping("/precios")
	public ResponseEntity<PreciosEntity> savePrecio(@RequestBody PreciosEntity precio) {
		PreciosEntity pre = preciosService.savePrecio(precio);
		if (pre == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pre);
	}
	
	@PutMapping("/precios")
	public ResponseEntity<PreciosEntity> updatePrecio(@RequestBody PreciosEntity precio) {
		PreciosEntity pre = preciosService.updatePrecio(precio.getId(), precio);
		if (pre == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pre);
	}
	
	@DeleteMapping("/precios/{id}")
	public ResponseEntity<String> deletePrecio(@PathVariable("id") Long id) {
		try {
			preciosService.removePrecio(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
