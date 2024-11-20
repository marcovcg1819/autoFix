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

import cl.usach.ms_catalogosr.entities.RecargoAntiguedadEntity;
import cl.usach.ms_catalogosr.services.RecargoAntiguedadService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class RecargoAntiguedadController {

	@Autowired
	private RecargoAntiguedadService recargoAntiguedadService;

	@GetMapping("/recargoantiguedad")
	public ResponseEntity<List<RecargoAntiguedadEntity>> getAllRecargos() {
		List<RecargoAntiguedadEntity> recargosList = recargoAntiguedadService.getAllRecargos();
		if (recargosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(recargosList);
	}

	@GetMapping("/recargoantiguedad/{id}")
	public ResponseEntity<RecargoAntiguedadEntity> getRecargoById(@PathVariable("id") Long id) {
		RecargoAntiguedadEntity rec = recargoAntiguedadService.getRecargoById(id);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}

	@PostMapping("/recargoantiguedad")
	public ResponseEntity<RecargoAntiguedadEntity> saveRecargo(@RequestBody RecargoAntiguedadEntity recargo) {
		RecargoAntiguedadEntity rec = recargoAntiguedadService.saveRecargo(recargo);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}
	
	@PutMapping("/recargoantiguedad")
	public ResponseEntity<RecargoAntiguedadEntity> updateRecargo(@RequestBody RecargoAntiguedadEntity recargo) {
		RecargoAntiguedadEntity rec = recargoAntiguedadService.updateRecargo(recargo.getId(), recargo);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}
	
	@DeleteMapping("/recargoantiguedad/{id}")
	public ResponseEntity<String> deletePrecio(@PathVariable("id") Long id) {
		try {
			recargoAntiguedadService.removeRecargo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
