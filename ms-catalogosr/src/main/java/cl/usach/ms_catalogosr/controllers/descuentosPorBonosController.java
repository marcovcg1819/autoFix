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

import cl.usach.ms_catalogosr.entities.DescuentosPorBonosEntity;
import cl.usach.ms_catalogosr.services.DescuentosPorBonosService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class descuentosPorBonosController {

	@Autowired
	private DescuentosPorBonosService descuentosPorBonosService;

	@GetMapping("/descuentosporbonos")
	public ResponseEntity<List<DescuentosPorBonosEntity>> getAllBonos() {
		List<DescuentosPorBonosEntity> bonosList = descuentosPorBonosService.getAllBonos();
		if (bonosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bonosList);
	}

	@GetMapping("/descuentosporbonos/{id}")
	public ResponseEntity<DescuentosPorBonosEntity> getBonoById(@PathVariable("id") Long id) {
		DescuentosPorBonosEntity bono = descuentosPorBonosService.getBonoById(id);
		if (bono == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bono);
	}

	@PostMapping("/descuentosporbonos")
	public ResponseEntity<DescuentosPorBonosEntity> saveBono(@RequestBody DescuentosPorBonosEntity bono) {
		DescuentosPorBonosEntity bon = descuentosPorBonosService.saveBono(bono);
		if (bon == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bon);
	}
	
	@PutMapping("/descuentosporbonos")
	public ResponseEntity<DescuentosPorBonosEntity> updateBono(@RequestBody DescuentosPorBonosEntity bono) {
		DescuentosPorBonosEntity bon = descuentosPorBonosService.updateBono(bono.getId(), bono);
		if (bon == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bon);
	}
	
	@DeleteMapping("/descuentosporbonos/{id}")
	public ResponseEntity<String> deleteBono(@PathVariable("id") Long id) {
		try {
			descuentosPorBonosService.removeBono(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
