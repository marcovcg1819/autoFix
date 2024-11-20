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

import cl.usach.ms_catalogosr.entities.TipoReparacionesEntity;
import cl.usach.ms_catalogosr.services.TipoReparacionesService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class TipoReparacionesController {
	@Autowired
	private TipoReparacionesService tipoReparacionesService;

	@GetMapping("/tiporeparaciones")
	public ResponseEntity<List<TipoReparacionesEntity>> getAllTipos() {
		List<TipoReparacionesEntity> tiposList = tipoReparacionesService.getAllTipos();
		if (tiposList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tiposList);
	}

	@GetMapping("/tiporeparaciones/{id}")
	public ResponseEntity<TipoReparacionesEntity> getTipoById(@PathVariable("id") Long id) {
		TipoReparacionesEntity tip = tipoReparacionesService.getTipoById(id);
		if (tip == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tip);
	}

	@PostMapping("/tiporeparaciones")
	public ResponseEntity<TipoReparacionesEntity> saveTipo(@RequestBody TipoReparacionesEntity tipo) {
		TipoReparacionesEntity tip = tipoReparacionesService.saveTipo(tipo);
		if (tip == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tip);
	}
	
	@PutMapping("/tiporeparaciones")
	public ResponseEntity<TipoReparacionesEntity> updateTipo(@RequestBody TipoReparacionesEntity tipo) {
		TipoReparacionesEntity tip = tipoReparacionesService.updateTipo(tipo.getId(), tipo);
		if (tip == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tip);
	}
	
	@DeleteMapping("/tiporeparaciones/{id}")
	public ResponseEntity<String> deleteTipo(@PathVariable("id") Long id) {
		try {
			tipoReparacionesService.removeTipo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
