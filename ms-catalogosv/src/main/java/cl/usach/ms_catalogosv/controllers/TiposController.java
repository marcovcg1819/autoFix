package cl.usach.ms_catalogosv.controllers;

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

import cl.usach.ms_catalogosv.entities.ModelosEntity;
import cl.usach.ms_catalogosv.entities.TiposEntity;
import cl.usach.ms_catalogosv.services.TiposService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class TiposController {
	@Autowired
	private TiposService tiposService;

	@GetMapping("/tipos")
	public ResponseEntity<List<TiposEntity>> getAllTipos() {
		List<TiposEntity> tiposList = tiposService.getAllTipos();
		if (tiposList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tiposList);
	}

	@GetMapping("/tipos/{id}")
	public ResponseEntity<TiposEntity> getTipoById(@PathVariable("id") Long id) {
		TiposEntity tipo = tiposService.getTipoById(id);
		if (tipo == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tipo);
	}

	@PostMapping("/tipos")
	public ResponseEntity<TiposEntity> saveTipo(@RequestBody TiposEntity tipo) {
		TiposEntity tip = tiposService.saveTipo(tipo);
		if (tip == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tip);
	}
	
	@PutMapping("/tipos")
	public ResponseEntity<TiposEntity> updateTipo(@RequestBody TiposEntity tipo) {
		TiposEntity tip = tiposService.updateTipo(tipo.getId(), tipo);
		if (tip == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(tip);
	}
	
	@DeleteMapping("/tipos/{id}")
	public ResponseEntity<String> deleteTipo(@PathVariable("id") Long id) {
		try {
			tiposService.removeTipo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
