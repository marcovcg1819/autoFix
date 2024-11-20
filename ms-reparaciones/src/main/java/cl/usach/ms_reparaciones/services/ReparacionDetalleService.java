package cl.usach.ms_reparaciones.services;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_reparaciones.entities.DescuentosBonosEntity;
import cl.usach.ms_reparaciones.entities.PreciosEntity;
import cl.usach.ms_reparaciones.entities.RecargoAntiguedadEntity;
import cl.usach.ms_reparaciones.entities.RecargoKilometrajeEntity;
import cl.usach.ms_reparaciones.entities.ReparacionDescuentosEntity;
import cl.usach.ms_reparaciones.entities.ReparacionDetalleEntity;
import cl.usach.ms_reparaciones.entities.ReparacionEntity;
import cl.usach.ms_reparaciones.entities.ReparacionSettingsEntity;
import cl.usach.ms_reparaciones.repositories.DescuentosBonosRepository;
import cl.usach.ms_reparaciones.repositories.PreciosRepository;
import cl.usach.ms_reparaciones.repositories.RecargoAntiguedadRepository;
import cl.usach.ms_reparaciones.repositories.RecargoKilometrajeRepository;
import cl.usach.ms_reparaciones.repositories.ReparacionDescuentosRepository;
import cl.usach.ms_reparaciones.repositories.ReparacionDetalleRepository;
import cl.usach.ms_reparaciones.repositories.ReparacionRepository;
import cl.usach.ms_reparaciones.repositories.ReparacionSettingsRepository;
import cl.usach.ms_reparaciones.util.UtilsClass;

@Service
public class ReparacionDetalleService {
	@Autowired
    ReparacionDetalleRepository reparacionDetalleRepository;
	@Autowired
    ReparacionRepository reparacionRepository;
	@Autowired
    PreciosRepository preciosRepository;
	@Autowired
    ReparacionDescuentosRepository reparacionDescuentosRepository;
	@Autowired
	UtilsClass utilClass;
	@Autowired
    ReparacionSettingsRepository reparacionSettingsRepository;
	@Autowired
    DescuentosBonosRepository descuentosBonosRepository;
	@Autowired
    RecargoKilometrajeRepository recargoKilometrajeRepository;
	@Autowired
    RecargoAntiguedadRepository recargoAntiguedadRepository;
	

	public List<ReparacionDetalleEntity> getAllDetalles(Long id){
		return reparacionDetalleRepository.findByIdReparacion(id);
	}
	
	public ReparacionDetalleEntity getDetalleById(Long id){
		return reparacionDetalleRepository.findById(id).get();
	}
	
	public ReparacionDetalleEntity saveDetalle(ReparacionDetalleEntity det){
		det.setFecha_reparacion(null);
		det.setHora_reparacion(null);
		PreciosEntity pre = preciosRepository.findById(det.getIdPrecio()).get();
		det.setMonto_reparacion(pre.getPrecio());
		return reparacionDetalleRepository.save(det);
	}
	
	public ReparacionDetalleEntity updateFechaHoraSalida(Date fecha, Time horaSalida, Long id){
		ReparacionDetalleEntity det = reparacionDetalleRepository.findById(id).get();
		det.setFecha_reparacion(fecha);
		det.setHora_reparacion(horaSalida);
		return reparacionDetalleRepository.save(det);
	}
	
	public ReparacionDetalleEntity updateDetalle(Long id , ReparacionDetalleEntity detalle){
		ReparacionDetalleEntity det = reparacionDetalleRepository.findById(id).get();
		
		det.setFecha_reparacion(null);
		det.setHora_reparacion(null);
		det.setIdReparacion(detalle.getIdReparacion());
		det.setIdPrecio(detalle.getIdPrecio());
//		det.setMonto_reparacion(detalle.getMonto_reparacion());//se calcula desde setId_reparacion_incluye
		PreciosEntity pre = preciosRepository.findById(detalle.getIdPrecio()).get();
		det.setMonto_reparacion(pre.getPrecio());
		det.setPatente(detalle.getPatente());
		det.setId_modelo(detalle.getId_modelo());
		
		reparacionDetalleRepository.save(det);
		return det;
	}
	
	public String removeDetalleReparacion(Long id){
		ReparacionDetalleEntity d = new ReparacionDetalleEntity();
		d.setId(id);
		reparacionDetalleRepository.delete(d);
		return "OK";
	}
	
	public String calcularRecargosDescuentos(Long idReparacion, Long idTipoVehiculo, Long idModelo, Integer kilometraje, Integer anioFabricacion){
		ReparacionEntity rep = reparacionRepository.findById(idReparacion).get();//reparacion
		List<ReparacionDetalleEntity> listReparaciones = reparacionDetalleRepository.findByIdReparacion(idReparacion);
		if(rep.getFecha_salida() == null) {
			return "Aun no se ha registrado de fecha de salida, no se puede calcular";
		}
		
		
//		calculo suma de reparaciones
		Float sumaReparaciones = 0f;
		for(ReparacionDetalleEntity dr:listReparaciones) {
			sumaReparaciones = Float.sum(sumaReparaciones, dr.getMonto_reparacion());
		}
//		calculo suma de reparaciones
		
//		calculo descuento por numero de reparaciones
		
		Float totalDescuentoNumeroReparaciones = 0f;
		
		Date d1 = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1); // menos un anio
		Date d2 = cal.getTime();
		
		Long countReparaciones = reparacionRepository.countReparaciones(d1, d2);
		
		System.out.println("count reparaciones :: "+countReparaciones);
		
		List<ReparacionDescuentosEntity> repDEscList = reparacionDescuentosRepository.findByIdTipoVehiculo(idTipoVehiculo);
		
		Integer inicio = 0;
		Integer fin = 0;
		for(ReparacionDescuentosEntity rd:repDEscList) {
			String[] parts = rd.getNumeroReparacionesUltimoAnio().split("–");
			inicio = Integer.parseInt(parts[0].trim());
			if(parts[1].trim().equalsIgnoreCase("mas")) {
				fin = 10000;
			}else {
				fin = Integer.parseInt(parts[1].trim());
			}
			Integer count = countReparaciones.intValue();
			
			if (count >= inicio && count <= fin) {//si esta dentro del rango
				totalDescuentoNumeroReparaciones = utilClass.valueMinusPercent(sumaReparaciones, rd.getDescuento_porcentaje());
			}
		}
//		calculo descuento por numero de reparaciones

//		calculo descuento por dia de atencion
		
		Float totalDescuentoDia = 0f;
		
		ReparacionSettingsEntity set = reparacionSettingsRepository.findByNombre("DESCUENTO_DIA_ATENCION");
		String[] partsSet = set.getValor().split(",");
		String[] partsDias = partsSet[0].split("-");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		Locale spanish = new Locale("es", "ES");
		String dia = new SimpleDateFormat("EE", spanish).format(date.getTime());
		dia = dia.substring(0,2);
//		String intervaloDia = "lu-ma-mi-ju-sá";
//		String[] intervaloDiaArray = intervaloDia.split("-");
		for(String str:partsDias) {
			System.out.println(str);
			if(str.equals(dia)) {
				System.out.println("aplica descuento");
				
				Date datea = new Date();   // given date
				Calendar calendara = GregorianCalendar.getInstance(); // creates a new calendar instance
				calendara.setTime(datea);   // assigns calendar to given date 
//				calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
//				calendar.get(Calendar.HOUR);        // gets hour in 12h format
//				calendar.get(Calendar.MONTH); 
				Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
				
				String[] intervaloHoraArray = partsSet[1].split("–");
//				String intervaloHora = "9-20";
//				String[] intervaloHoraArray = intervaloHora.split("-");
				Integer horaI = Integer.valueOf(intervaloHoraArray[0]);
				Integer horaF = Integer.valueOf(intervaloHoraArray[1]);
				
				if(hour >= horaI && hour <= horaF) {
					System.out.println("aplica descuento por hora");
					Long porcentaje = Long.valueOf(partsSet[2]);
					
					totalDescuentoDia = utilClass.valueMinusPercent(sumaReparaciones, porcentaje);
				}
				
				System.out.println(hour);
			}
		}
		System.out.println(dia);
//		calculo descuento por dia de atencion

		
//		calculo recargo por kilometraje
		Float totalRecargoKilometraje = 0f;
		List<RecargoKilometrajeEntity> recKilcList = recargoKilometrajeRepository.findByIdModelo(idModelo);
		for(RecargoKilometrajeEntity rk:recKilcList) {
			String[] intervalos = rk.getIntervalo().split("–");
			Integer intervaloA = Integer.valueOf(intervalos[0].trim());
			Integer intervaloB = 10000;
			if(!intervalos[1].trim().equalsIgnoreCase("mas")) {
				intervaloB = Integer.valueOf(intervalos[1].trim());
			}
			
			if(kilometraje >= intervaloA && kilometraje <= intervaloB) {
				System.out.println("aplica recargo por kilometraje");
				totalRecargoKilometraje = utilClass.valueMinusPercent(sumaReparaciones, rk.getDescuento_porcentaje());
			}
		}
//		calculo recargo por kilometraje
		
		
//		calculo recargo por antiguedad
		Float totalRecargoAntiguedad = 0f;
		List<RecargoAntiguedadEntity> recAntList = recargoAntiguedadRepository.findByIdModelo(idModelo);
		for(RecargoAntiguedadEntity ra : recAntList) {
			String[] intervalosAnios = ra.getIntervalo_anios().split("–");
			Integer intervaloAniosA = Integer.valueOf(intervalosAnios[0].trim());
			Integer intervaloAniosB = 10000;
			if(!intervalosAnios[1].trim().equalsIgnoreCase("mas")) {
				intervaloAniosB = Integer.valueOf(intervalosAnios[1].trim());
			}
			
			Date dActual = new Date();
			
			Integer anios = dActual.getYear() - anioFabricacion;
			
			
			if(anios >= intervaloAniosA && anios <= intervaloAniosB) {
				System.out.println("aplica recargo por antiguedad");
				totalRecargoAntiguedad = utilClass.valueMinusPercent(sumaReparaciones, ra.getDescuento_porcentaje());
			}
		}
		
//		calculo recargo por antiguedad
		
		
//		calculo recargo recogida
		
		Float totalRecargoRecogida = 0f;
		Float recargoRecogida = 0f;
		
		ReparacionSettingsEntity setDia = reparacionSettingsRepository.findByNombre("RECARGO_RETRASO_RECOGIDA");
		String[] partsSetDia = setDia.getValor().split(",");
		Date dateaDia = new Date(); 
		rep.getFecha_salida();
		
		long diff = dateaDia.getTime() - rep.getFecha_salida().getTime();
	    System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		Long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		if(days > 0 ) {
			for(int i = 1; i <= days; i++) {
				recargoRecogida = sumaReparaciones - utilClass.valueMinusPercent(sumaReparaciones, Long.valueOf(partsSetDia[0]));
				totalRecargoRecogida = totalRecargoRecogida + recargoRecogida;
			}
		}
//		calculo recargo recogida		
	
		
		rep.setMonto_total_reparaciones(sumaReparaciones);


		sumaReparaciones = sumaReparaciones
				- totalDescuentoNumeroReparaciones 
				- totalDescuentoDia 
				+ totalRecargoKilometraje
				+ totalRecargoAntiguedad
				+ totalRecargoRecogida;

		
//		calculo iva	
		
		Float iva = 0f;
		ReparacionSettingsEntity setIva = reparacionSettingsRepository.findByNombre("IVA");
		iva = Float.valueOf(setIva.getValor());
		iva = sumaReparaciones - sumaReparaciones*iva;
		
		sumaReparaciones = sumaReparaciones + iva;
//		calculo iva	
				
		rep.setTotal(sumaReparaciones);
		rep.setMonto_total_recargos(totalRecargoKilometraje+totalRecargoAntiguedad+totalRecargoRecogida+iva);
//		rep.setMonto_total_reparaciones(totalDescuentoNumeroReparaciones+totalDescuentoDia);
		rep.setMonto_iva(iva);
		
		reparacionRepository.save(rep);
		
		return "OK";
	}
	
	public String aplicarDescuentoBono(Long idReparacion, Long idBono){
		ReparacionEntity rep = reparacionRepository.findById(idReparacion).get();//reparacion
		DescuentosBonosEntity desc = descuentosBonosRepository.findById(idBono).get();
		Float totalDescuento = 0f;
		
		if(desc.getNumero_bono() == 0) {
			return "No se pude aplicar descuento, ya no hay disponibles";
		}else {
			totalDescuento = rep.getTotal() - desc.getDinero_bono();
			
			rep.setTotal(totalDescuento);
			
			reparacionRepository.save(rep);
			
			desc.setNumero_bono(desc.getNumero_bono() - 1);
			
			descuentosBonosRepository.save(desc);
			
			reparacionRepository.save(rep);
			
			return "OK";
		}
		
	}
}
