package cl.usach.ms_reportes.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cl.usach.ms_reportes.models.ListaReparacion;
import cl.usach.ms_reportes.models.Reporte1;
import cl.usach.ms_reportes.models.Reporte2Resp;
import cl.usach.ms_reportes.models.RequestFechaInicialFechaFinal;
import cl.usach.ms_reportes.models.ResponseListReparaciones;
import cl.usach.ms_reportes.services.ReportesService;

@RestController
@RequestMapping("/reportes")
@CrossOrigin("*")
public class ReportesController {
	
	@Autowired
	private ReportesService reportesService;
	
	@PostMapping("/reporte1")
	public ResponseEntity<Collection<Reporte1>> getListByMonth(@RequestBody RequestFechaInicialFechaFinal fechas){
		Collection<Reporte1> reporte1 = reportesService.getReporte1(fechas.getFechaInicial(), fechas.getFechaFinal());
	        if(reporte1.isEmpty())
	            return ResponseEntity.noContent().build();
		return ResponseEntity.ok(reporte1);
	}
	
	@GetMapping("/reporte2/{mes}/{anio}")
	public ResponseEntity<List<Reporte2Resp>> getListByMonth2(@PathVariable ("mes") int mes, @PathVariable ("anio") int anio){
	        List<Reporte2Resp> rep2 = reportesService.getReporte2(mes, anio);
	        if(rep2.isEmpty())
	            return ResponseEntity.noContent().build();
		return ResponseEntity.ok(rep2);
	}
	
	@GetMapping("/listareparaciones")
	public ResponseEntity<List<ResponseListReparaciones>> getListaReparacion() throws JsonMappingException, JsonProcessingException {
		List<ResponseListReparaciones> lista = reportesService.getListaReparaciones();
	        if(lista.isEmpty())
	            return ResponseEntity.noContent().build();
		return ResponseEntity.ok(lista);
	}

}
