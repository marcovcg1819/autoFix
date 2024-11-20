package cl.usach.ms_reparaciones.controllers;

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

import cl.usach.ms_reparaciones.entities.ReparacionDetalleEntity;
import cl.usach.ms_reparaciones.models.RequestReparacionHoraFechaSalida;
import cl.usach.ms_reparaciones.services.ReparacionDetalleService;

@RestController
@RequestMapping("/reparaciondetalle")
@CrossOrigin("*")
public class ReparacionDetalleController {

	@Autowired
    private ReparacionDetalleService reparacionDetalleService;
    
    @GetMapping("/detallebyreparacion/{id}")
	public ResponseEntity<List<ReparacionDetalleEntity>> getAllDetalles(@PathVariable("id") Long id) {
		List<ReparacionDetalleEntity> detalleList = reparacionDetalleService.getAllDetalles(id);
		if (detalleList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(detalleList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReparacionDetalleEntity> getDetalleId(@PathVariable("id") Long id) {
		ReparacionDetalleEntity det = reparacionDetalleService.getDetalleById(id);
		if (det == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(det);
	}

	@PostMapping
	public ResponseEntity<ReparacionDetalleEntity> saveDetalle(@RequestBody ReparacionDetalleEntity detalle) {
		ReparacionDetalleEntity det = reparacionDetalleService.saveDetalle(detalle);
		if (det == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(det);
	}
	
	@PutMapping("/updatefechahorasalida/{id}")
	public ResponseEntity<ReparacionDetalleEntity> updateFechaHoraSalida(@PathVariable("id") Long detalleid, @RequestBody RequestReparacionHoraFechaSalida detalle) {
		ReparacionDetalleEntity det = reparacionDetalleService.updateFechaHoraSalida(detalle.getFecha_salida(), detalle.getHora_salida(), detalleid);
		if (det == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(det);
	}
	
	@PutMapping
	public ResponseEntity<ReparacionDetalleEntity> updateDEtalle(@RequestBody ReparacionDetalleEntity detalle) {
		ReparacionDetalleEntity det = reparacionDetalleService.updateDetalle(detalle.getId(), detalle);
		if (det == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(det);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDetalle(@PathVariable("id") Long id) {
		try {
			reparacionDetalleService.removeDetalleReparacion(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
