package cl.usach.ms_reparaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import cl.usach.ms_reparaciones.entities.ReparacionEntity;
import cl.usach.ms_reparaciones.models.RequestAplicarDescuentoBono;
import cl.usach.ms_reparaciones.models.RequestRecargosDescuentos;
import cl.usach.ms_reparaciones.models.RequestReparacionHoraFechaSalida;
import cl.usach.ms_reparaciones.services.ReparacionDetalleService;
import cl.usach.ms_reparaciones.services.ReparacionService;

@RestController
@RequestMapping("/reparacion")
@CrossOrigin("*")
public class ReparacionController {
    
    private final ReparacionService reparacionService;
    @Autowired
    private ReparacionDetalleService reparacionDetalleService;
    
    
    @Autowired 
    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }
    
    @GetMapping
	public ResponseEntity<List<ReparacionEntity>> getAllReparaciones() {
		List<ReparacionEntity> reparacionesList = reparacionService.getAllReparaciones();
		if (reparacionesList == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reparacionesList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReparacionEntity> getTipoReparacionId(@PathVariable("id") Long id) {
		ReparacionEntity rep = reparacionService.getReparacionById(id);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}

	@PostMapping
	public ResponseEntity<ReparacionEntity> saveReparacion(@RequestBody ReparacionEntity reparacion) {
		ReparacionEntity rep = reparacionService.saveReparacion(reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	@PutMapping
	public ResponseEntity<ReparacionEntity> updateReparacion(@RequestBody ReparacionEntity reparacion) {
		ReparacionEntity rep = reparacionService.updateReparacion(reparacion.getId(), reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	@PutMapping("/updatehorafechasalida/{id}")
	public ResponseEntity<ReparacionEntity> updateHoraFechaSalida(@PathVariable("id") Long id, @RequestBody RequestReparacionHoraFechaSalida reparacion) {
		ReparacionEntity rep = reparacionService.updateHoraFechaSalida(id, reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	
	@PutMapping("/updatehorafechaentrega/{id}")
	public ResponseEntity<ReparacionEntity> updateHoraFechaEntrega(@PathVariable("id") Long id, @RequestBody RequestReparacionHoraFechaSalida reparacion) {
		ReparacionEntity rep = reparacionService.updateHoraFechaEntregaCliente(id, reparacion);
		if (rep == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(rep);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReparacion(@PathVariable("id") Long id) {
		try {
//			reparacionService.removeReparacion(id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/calculardescuentosrecargos")
	public ResponseEntity<String> calcularDescuentosCargos(@RequestBody RequestRecargosDescuentos rep) {
		String resp = reparacionDetalleService
				.calcularRecargosDescuentos(rep.getIdReparacion(), rep.getIdTipoVehiculo() 
						,rep.getIdModelo(), rep.getKilometraje(), rep.getAnioFabricacion());
		if (resp == null)
			return ResponseEntity.notFound().build();
		if (!resp.equalsIgnoreCase("OK"))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/aplicardescuentobono")
	public ResponseEntity<String> aplicarDescuentoBono(@RequestBody RequestAplicarDescuentoBono rep) {
		String resp = reparacionDetalleService.aplicarDescuentoBono(rep.getIdReparacion(), rep.getIdBono());
		if (resp == null)
			return ResponseEntity.notFound().build();
		if (!resp.equals("OK"))
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(resp);
	}
    
    
//    @Autowired
//    private TipoReparacionService tipoReparacionService;
//
//    @GetMapping
//    public ResponseEntity<List<ReparacionEntity>> getAll() {
//        List<ReparacionEntity> reparaciones = reparacionService.getAll();
//        if(reparaciones.isEmpty())
//            return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(reparaciones);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ReparacionEntity> getById(@PathVariable ("id") Long id) {
//        ReparacionEntity reparacion = reparacionService.getReparacionById(id);
//        if(reparacion == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(reparacion);
//    }
//    
//    @GetMapping("/tiposreparacion")
//    public ResponseEntity<List<TipoReparacionesEntity>> getById() {
//        List<TipoReparacionesEntity> tipos = tipoReparacionService.gettiposReparacion();
//        if(tipos == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(tipos);
//    }
//    
//    @GetMapping("/{mes}/{anio}")
//    public ResponseEntity<List<ReparacionEntity>> getByMesAnio(@PathVariable ("mes") int mes, @PathVariable ("anio") int anio) {
//        List<ReparacionEntity> reparaciones = reparacionService.getByMesAnio(mes, anio);
//        if(reparaciones == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(reparaciones);
//    }
//    
//    
//    @GetMapping("/reporte2/{mes}/{anio}")
//    public ResponseEntity<Collection<Reporte2Res>> getByDosMesesYActual(@PathVariable ("mes") int mes, @PathVariable ("anio") int anio) {
//        Collection<Reporte2Res> reparaciones = reparacionService.getReporte2(mes, anio);
//        if(reparaciones == null)
//            return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(reparaciones);
//    }
//
//    @PostMapping()
//    public ResponseEntity<ReparacionEntity> save(@RequestBody ReparacionReq reparacion) {
//        ReparacionEntity reparacionNew = reparacionService.saveReparacion(reparacion);
//        return ResponseEntity.ok(reparacionNew);
//    }
//
//    @GetMapping("/reparaciondesc/{patente}/{tipo_motor}")
//    public ResponseEntity<Integer> reparaciondesc(@PathVariable String patente, @PathVariable String tipo_motor) {
//        Integer reparaciondesc = reparacionService.reparaciondesc(patente, tipo_motor);
//        return ResponseEntity.ok(reparaciondesc);
//    }
//
//    @PatchMapping("/modificarSalida/{id}/{fecha_salida}/{hora_salida}")
//    public ResponseEntity<ReparacionEntity> modificarReparacionSalida(@PathVariable Long id, @PathVariable String fecha_salida,@PathVariable String hora_salida) {
//        ReparacionEntity reparacionModificado = reparacionService.modificarReparacionSalida(id, fecha_salida, hora_salida);
//        return ResponseEntity.ok(reparacionModificado);
//    }
//
//    @PatchMapping("/modificarListo/{id}/{fecha_salida}/{hora_salida}")
//    public ResponseEntity<ReparacionEntity> modificarReparacionListo(@PathVariable Long id, @PathVariable Timestamp fecha_salida,@PathVariable String hora_salida) {
//        ReparacionEntity reparacionModificado = reparacionService.modificarReparacionListo(id, fecha_salida, hora_salida);
//        return ResponseEntity.ok(reparacionModificado);
//    }


}
