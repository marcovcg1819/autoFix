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

import cl.usach.ms_catalogosr.entities.ReparacionIncluyeEntity;
import cl.usach.ms_catalogosr.services.ReparacionIncluyeService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class ReparacionIncluyeController {
	
	@Autowired
	private ReparacionIncluyeService reparacionIncluyeService;

	@GetMapping("/reparacionincluye")
	public ResponseEntity<List<ReparacionIncluyeEntity>> getAllRIncluye() {
		List<ReparacionIncluyeEntity> reparacionList = reparacionIncluyeService.getAllRIncluye();
		if (reparacionList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reparacionList);
	}

	@GetMapping("/reparacionincluye/{id}")
	public ResponseEntity<ReparacionIncluyeEntity> getRIncluyeById(@PathVariable("id") Long id) {
		ReparacionIncluyeEntity rep = reparacionIncluyeService.getRIncluyeById(id);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}

	@PostMapping("/reparacionincluye")
	public ResponseEntity<ReparacionIncluyeEntity> saveRIncluye(@RequestBody ReparacionIncluyeEntity reparacion) {
		ReparacionIncluyeEntity rep = reparacionIncluyeService.saveRIncluye(reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	@PutMapping("/reparacionincluye")
	public ResponseEntity<ReparacionIncluyeEntity> updaterIncluye(@RequestBody ReparacionIncluyeEntity reparacion) {
		ReparacionIncluyeEntity rep = reparacionIncluyeService.updateRIncluye(reparacion.getId(), reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	@DeleteMapping("/reparacionincluye/{id}")
	public ResponseEntity<String> deleteRIncluye(@PathVariable("id") Long id) {
		try {
			reparacionIncluyeService.removeRIncluye(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
