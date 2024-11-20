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

import cl.usach.ms_catalogosv.entities.MarcasEntity;
import cl.usach.ms_catalogosv.services.MarcasService;

@RestController
@RequestMapping("/cats")
@CrossOrigin("*")
public class MarcasController {
	
	@Autowired
	private MarcasService marcasService;

	@GetMapping("/marcas")
	public ResponseEntity<List<MarcasEntity>> getAllMarcas() {
		List<MarcasEntity> marcasList = marcasService.getAllMarcas();
		if (marcasList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(marcasList);
	}

	@GetMapping("/marcas/{id}")
	public ResponseEntity<MarcasEntity> getMarcaById(@PathVariable("id") Long id) {
		MarcasEntity marca = marcasService.getMarcaById(id);
		if (marca == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(marca);
	}

	@PostMapping("/marcas")
	public ResponseEntity<MarcasEntity> saveMarca(@RequestBody MarcasEntity marca) {
		MarcasEntity mar = marcasService.saveMarca(marca);
		if (mar == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(mar);
	}
	
	@PutMapping("/marcas")
	public ResponseEntity<MarcasEntity> updateMarca(@RequestBody MarcasEntity marca) {
		MarcasEntity mar = marcasService.updateMarca(marca.getId(), marca);
		if (mar == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(mar);
	}
	
	@DeleteMapping("/marcas/{id}")
	public ResponseEntity<String> deleteMarca(@PathVariable("id") Long id) {
		try {
			marcasService.removeMarca(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
