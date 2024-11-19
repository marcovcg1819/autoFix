package cl.usach.ms_reparaciones.controllers;

import cl.usach.ms_reparaciones.entities.ReparacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.usach.ms_reparaciones.services.ReparacionService;

import java.util.List;

@RestController
@RequestMapping("/reparacion")
@CrossOrigin("*")
public class ReparacionController {
    @Autowired
    private ReparacionService reparacionService;

    @GetMapping
    public ResponseEntity<List<ReparacionEntity>> getAll() {
        List<ReparacionEntity> reparaciones = reparacionService.getAll();
        if(reparaciones.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(reparaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparacionEntity> getById(@PathVariable ("id") int id) {
        ReparacionEntity reparacion = reparacionService.getReparacionById(id);
        if(reparacion == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reparacion);
    }

    @PostMapping()
    public ResponseEntity<ReparacionEntity> save(@RequestBody ReparacionEntity reparacion) {
        ReparacionEntity reparacionNew = reparacionService.saveReparacion(reparacion);
        return ResponseEntity.ok(reparacionNew);
    }

    @GetMapping("/reparaciondesc/{patente}/{tipo_motor}")
    public ResponseEntity<Integer> reparaciondesc(@PathVariable String patente, @PathVariable String tipo_motor) {
        Integer reparaciondesc = reparacionService.reparaciondesc(patente, tipo_motor);
        return ResponseEntity.ok(reparaciondesc);
    }

    @PatchMapping("/modificarSalida/{id}/{fecha_salida}/{hora_salida}")
    public ResponseEntity<ReparacionEntity> modificarReparacionSalida(@PathVariable String id, @PathVariable String fecha_salida,@PathVariable String hora_salida) {
        ReparacionEntity reparacionModificado = reparacionService.modificarReparacionSalida(id, fecha_salida, hora_salida);
        return ResponseEntity.ok(reparacionModificado);
    }

    @PatchMapping("/modificarListo/{id}/{fecha_salida}/{hora_salida}")
    public ResponseEntity<ReparacionEntity> modificarReparacionListo(@PathVariable String id, @PathVariable String fecha_salida,@PathVariable String hora_salida) {
        ReparacionEntity reparacionModificado = reparacionService.modificarReparacionListo(id, fecha_salida, hora_salida);
        return ResponseEntity.ok(reparacionModificado);
    }


}
