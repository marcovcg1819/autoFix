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

import cl.usach.ms_catalogosr.entities.RecargoKilometrajeEntity;
import cl.usach.ms_catalogosr.services.RecargoKilometrajeService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class RecargoKilometrajeController {
	@Autowired
	private RecargoKilometrajeService recargoKilometrajeService;

	@GetMapping("/recargokilometraje")
	public ResponseEntity<List<RecargoKilometrajeEntity>> getAllRecargos() {
		List<RecargoKilometrajeEntity> recargosList = recargoKilometrajeService.getAllRecargos();
		if (recargosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(recargosList);
	}

	@GetMapping("/recargokilometraje/{id}")
	public ResponseEntity<RecargoKilometrajeEntity> getRecargoById(@PathVariable("id") Long id) {
		RecargoKilometrajeEntity rec = recargoKilometrajeService.getRecargoById(id);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}

	@PostMapping("/recargokilometraje")
	public ResponseEntity<RecargoKilometrajeEntity> saveRecargo(@RequestBody RecargoKilometrajeEntity recargo) {
		RecargoKilometrajeEntity rec = recargoKilometrajeService.saveRecargo(recargo);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}
	
	@PutMapping("/recargokilometraje")
	public ResponseEntity<RecargoKilometrajeEntity> updateRecargo(@RequestBody RecargoKilometrajeEntity recargo) {
		RecargoKilometrajeEntity rec = recargoKilometrajeService.updateRecargo(recargo.getId(), recargo);
		if (rec == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rec);
	}
	
	@DeleteMapping("/recargokilometraje/{id}")
	public ResponseEntity<String> deletePrecio(@PathVariable("id") Long id) {
		try {
			recargoKilometrajeService.removeRecargo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
