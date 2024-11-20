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
import cl.usach.ms_catalogosv.services.ModelosService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class ModelosController {
	@Autowired
	private ModelosService modelosService;

	@GetMapping("/modelos")
	public ResponseEntity<List<ModelosEntity>> getAllModelos() {
		List<ModelosEntity> modelosList = modelosService.getAllModelos();
		if (modelosList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(modelosList);
	}

	@GetMapping("/modelos/{id}")
	public ResponseEntity<ModelosEntity> getModeloById(@PathVariable("id") Long id) {
		ModelosEntity modelo = modelosService.getModeloById(id);
		if (modelo == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(modelo);
	}

	@PostMapping("/modelos")
	public ResponseEntity<ModelosEntity> saveModelo(@RequestBody ModelosEntity modelo) {
		ModelosEntity mod = modelosService.saveModelo(modelo);
		if (mod == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(mod);
	}
	
	@PutMapping("/modelos")
	public ResponseEntity<ModelosEntity> updateModelo(@RequestBody ModelosEntity modelo) {
		ModelosEntity mod = modelosService.updateModelo(modelo.getId(), modelo);
		if (mod == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(mod);
	}
	
	@DeleteMapping("/modelos/{id}")
	public ResponseEntity<String> deleteModelo(@PathVariable("id") Long id) {
		try {
			modelosService.removeModelo(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
