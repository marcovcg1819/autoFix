package cl.usach.ms_reportes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.usach.ms_reportes.entities.TipoReparacionesEntity;
import cl.usach.ms_reportes.models.ListaReparacion;
import cl.usach.ms_reportes.models.Reporte1;
import cl.usach.ms_reportes.models.Reporte2;
import cl.usach.ms_reportes.models.Reporte2List;
import cl.usach.ms_reportes.models.Reporte2Resp;
import cl.usach.ms_reportes.models.ResponseListReparaciones;
import cl.usach.ms_reportes.models.ResponseListVeviculos;
import cl.usach.ms_reportes.repositories.ReparacionRepository;
import cl.usach.ms_reportes.repositories.TipoReparacionesRepository;

@Service
public class ReportesService {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private final String ENDPOINT_VEHICULO_PATENTE = "/vehiculos/vehiculo/bypatente/";
	
	@Value("${gateway.url}")
	private String gatewayUrl;
	
	@Autowired
	private ReparacionRepository reparacionRepository;
	@Autowired
	private TipoReparacionesRepository tipoReparacionesRepository;
	
	
	public Collection<Reporte1> getReporte1(Date fecha1, Date fecha2){
		
		return reparacionRepository.getReporte1(fecha1, fecha2);
		
	}
	
	public List<Reporte2Resp> getReporte2(Integer mes, Integer anio){
		
		List<TipoReparacionesEntity> tiposList = tipoReparacionesRepository.findAll();
		List<Reporte2Resp> response = new ArrayList<Reporte2Resp>();
		Reporte2Resp repRes = null;
		int i = 0;
		for(TipoReparacionesEntity tr:tiposList) {
			
			List<Reporte2List> mes1 = getReporte2Query(mes, anio, 1, tr.getTipo());
			List<Reporte2List> mes2 = getReporte2Query(mes, anio, 2, tr.getTipo());
			List<Reporte2List> mes3 = getReporte2Query(mes, anio, 3, tr.getTipo());
			
			System.out.println("iiiii :"+i);
			
			repRes = new Reporte2Resp();
			System.out.println("tr.getTipo() :"+tr.getTipo());
			repRes.setTipoReparacion(tr.getTipo());
			if(mes1.size() != 0) {
				if(mes1.get(0).getTipo().equals(tr.getTipo())) {
					repRes.setCantidadMes1(mes1.get(0).getCantidadMes());
					repRes.setMontoMes1(mes1.get(0).getMontoMes());
				}
			}else {
//				repRes.setTipoReparacion("");
				repRes.setCantidadMes1(0);
				repRes.setMontoMes1(0f);
				
				repRes.setVariacionMes2(Float.valueOf(100*(0/1)));
				repRes.setVariacionMontoMes2(Float.valueOf(100*(0/1)));
			}
			
			//System.out.println("mes1.get(i).getCantidadMes() :"+mes1.get(i).getCantidadMes());
			
			
			if(mes2.size() != 0) {
				if(mes2.get(0).getTipo().equals(tr.getTipo())) {
					repRes.setCantidadMes2(mes2.get(0).getCantidadMes());
					repRes.setMontoMes2(mes2.get(0).getMontoMes());
					repRes.setVariacionMes2(Float.valueOf(100*(mes1.get(0).getCantidadMes()-mes2.get(0).getCantidadMes()/mes2.get(0).getCantidadMes())));
					repRes.setVariacionMontoMes2(Float.valueOf(100*(mes1.get(0).getMontoMes()-mes2.get(0).getMontoMes()/mes2.get(0).getMontoMes())));
				}
			}else{
				repRes.setCantidadMes2(0);
				repRes.setMontoMes2(0f);
				
				repRes.setVariacionMes3(Float.valueOf(100*(0/1)));
				repRes.setVariacionMontoMes3(Float.valueOf(100*(0/1)));
				if(mes1.size() != 0) {
					repRes.setVariacionMes2(Float.valueOf(100*(mes1.get(0).getCantidadMes()-0/1)));
					repRes.setVariacionMontoMes2(Float.valueOf(100*(mes1.get(0).getMontoMes()-0/1)));
				}
			}
			
			if(mes3.size() != 0) {
				if(mes3.get(0).getTipo().equals(tr.getTipo())) {
					repRes.setCantidadMes3(mes3.get(0).getCantidadMes());
					repRes.setMontoMes3(mes3.get(0).getMontoMes());
					repRes.setVariacionMes3(Float.valueOf(100*(mes2.get(0).getCantidadMes()-mes3.get(0).getCantidadMes()/mes3.get(0).getCantidadMes())));
					repRes.setVariacionMontoMes3(Float.valueOf(100*(mes2.get(0).getMontoMes()-mes3.get(0).getMontoMes()/mes3.get(0).getMontoMes())));
				}
			}else {
				repRes.setCantidadMes3(0);
				repRes.setMontoMes3(0f);
				if(mes2.size() != 0) {
					repRes.setVariacionMes3(Float.valueOf(100*(mes2.get(0).getCantidadMes()-0/1)));
					repRes.setVariacionMontoMes3(Float.valueOf(100*(mes2.get(0).getMontoMes()-0/1)));
				}
			}
//			if(!repRes.getTipoReparacion().equals("")) {
				response.add(repRes);
//			}
			
			i++;
		}
		
		return response;
		
	}
	
	public List<Reporte2List> getReporte2Query(Integer mes, Integer anio, Integer minus, String tipo){
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio,mes,1,0,0);//month is 0 based
		calendar.add(Calendar.MONTH, -minus);
		Date date1 = calendar.getTime();
		
		
		
		Calendar calendar2 = Calendar.getInstance();
		
		calendar2.set(anio,mes,1,0,0);//month is 0 based
		calendar2.set(Calendar.DATE, calendar2.getActualMaximum(Calendar.DATE));
		calendar2.add(Calendar.MONTH, -minus);
		
		
		
		Date date2 = calendar2.getTime();
		
		System.out.println(date1.toString());
        System.out.println(date2.toString());
        Collection<Reporte2> col = reparacionRepository.getReporte2(date1, date2, tipo);
        List<Reporte2List> theList = new ArrayList<Reporte2List>();
        
        Reporte2List rl = null;
        Iterator<? extends Reporte2> iter = col.iterator();
        while (iter.hasNext()) {
        	rl = new Reporte2List();
        	Reporte2 item = iter.next();
        	System.out.println("item.getTipo() ::: "+item.getTipo());
        	rl.setTipo(item.getTipo());
        	 System.out.println("item.getCatidadmes() ::: "+item.getCantidadmes());
        	rl.setCantidadMes(item.getCantidadmes());
        	 System.out.println("item.getMontomes() ::: "+item.getMontomes());
        	rl.setMontoMes(item.getMontomes());
        	theList.add(rl);
        }
		
		return theList;
		
	}
	
	public List<ResponseListReparaciones> getListaReparaciones() throws JsonMappingException, JsonProcessingException{
		
		String url = gatewayUrl+ENDPOINT_VEHICULO_PATENTE;
		
		ResponseListVeviculos obj1 = new ResponseListVeviculos();
		ResponseListVeviculos vehiculo = new ResponseListVeviculos();
		Collection<ListaReparacion> col = reparacionRepository.getLista();
		List<ResponseListReparaciones> theList = new ArrayList<>();
		
		ResponseListReparaciones rl = null;
        Iterator<? extends ListaReparacion> iter = col.iterator();
        while (iter.hasNext()) {
        	rl = new ResponseListReparaciones();
        	ListaReparacion item = iter.next();
        	
        	
        	rl.setId(item.getId());
        	rl.setFecha_ingreso(item.getFecha_ingreso());
        	rl.setHora_ingreso(item.getHora_ingreso());
        	rl.setMonto_total_reparaciones(item.getMonto_total_reparaciones());
        	rl.setMonto_total_recargos(item.getMonto_total_recargos());
        	rl.setMonto_iva(item.getMonto_iva());
        	rl.setTotal(item.getTotal());
        	rl.setFecha_salida(item.getFecha_salida());
        	rl.setHora_salida(item.getHora_salida());
        	rl.setFecha_entrega_cliente(item.getFecha_entrega_cliente());
        	rl.setHora_entrega_cliente(item.getHora_entrega_cliente());
        	rl.setPatente(item.getPatente());
        	rl.setMonto_reparacion(item.getMonto_reparacion());
        	rl.setFecha_reparacion(item.getFecha_reparacion());
        	rl.setHora_reparacion(item.getHora_reparacion());
        	rl.setModelos(item.getModelos());
        	
        	//vehiculo
        	
        	HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    		headers.setContentType(MediaType.APPLICATION_JSON);
    		HttpEntity<String> packetEntity = new HttpEntity(obj1, headers);

    		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    		ResponseEntity<String> result = restTemplate.exchange(url+item.getPatente(), HttpMethod.GET, packetEntity,
    				String.class);
    		vehiculo = objectMapper.readValue(result.getBody(), new TypeReference<ResponseListVeviculos>() {
    		});
        	
    		rl.setMarca(vehiculo.getMarca());
    		rl.setTipo(vehiculo.getTipo());
    		rl.setAnio_fabricacion(vehiculo.getAnio_fabricacion());
    		rl.setKilometraje(vehiculo.getKilometraje());
    		rl.setN_asientos(vehiculo.getN_asientos());
        	
        	
        	theList.add(rl);
        }
		
		
		
		return theList;
		
	}
	
}
