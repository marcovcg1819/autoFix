package cl.usach.ms_reparaciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.usach.ms_reparaciones.entities.ReparacionEntity;
import cl.usach.ms_reparaciones.models.RequestReparacionHoraFechaSalida;
import cl.usach.ms_reparaciones.repositories.ReparacionRepository;

@Service
public class ReparacionService {

    @Autowired
    ReparacionRepository reparacionRepository;
    
    @Autowired
    public ReparacionService(){
      // TODO document why this constructor is empty
    }

	public List<ReparacionEntity> getAllReparaciones(){
		return reparacionRepository.findAll();
	}
	
	public ReparacionEntity getReparacionById(Long id){
		return reparacionRepository.findById(id).get();
	}
	
	public ReparacionEntity saveReparacion(ReparacionEntity rep){
		rep.setFecha_entrega_cliente(null);
		rep.setHora_entrega_cliente(null);
		rep.setFecha_salida(null);
		rep.setHora_salida(null);
		
		rep.setMonto_iva(null);
		rep.setMonto_total_recargos(null);
		rep.setMonto_total_reparaciones(null);
		rep.setTotal(null);
		rep.setFecha_salida(null);
		return reparacionRepository.save(rep);
	}
	
	public ReparacionEntity updateReparacion(Long id , ReparacionEntity reparacion){
		ReparacionEntity rep = reparacionRepository.findById(id).get();
		
		rep.setFecha_entrega_cliente(null);
		rep.setHora_entrega_cliente(null);
		rep.setFecha_salida(null);
		rep.setHora_salida(null);
		
		rep.setMonto_iva(null);
		rep.setMonto_total_recargos(null);
		rep.setMonto_total_reparaciones(null);
		rep.setTotal(null);
		rep.setFecha_salida(null);
		
		rep.setFecha_ingreso(reparacion.getFecha_ingreso());
		
		reparacionRepository.save(rep);
		return rep;
	}
	
	public ReparacionEntity updateHoraFechaSalida(Long id , RequestReparacionHoraFechaSalida reparacion){
		ReparacionEntity rep = reparacionRepository.findById(id).get();
		
		rep.setFecha_salida(reparacion.getFecha_salida());
		rep.setHora_salida(reparacion.getHora_salida());
		
		reparacionRepository.save(rep);
		return rep;
	}
	
	public ReparacionEntity updateHoraFechaEntregaCliente(Long id , RequestReparacionHoraFechaSalida reparacion){
		ReparacionEntity rep = reparacionRepository.findById(id).get();
		
		rep.setFecha_entrega_cliente(reparacion.getFecha_salida());
		rep.setHora_entrega_cliente(reparacion.getHora_salida());
		
		reparacionRepository.save(rep);
		return rep;
	}
	
	public String removeReparacion(Long id){
		ReparacionEntity r = new ReparacionEntity();
		r.setId(id);
//		reparacionRepository.delete(r);
		return "OK";
	}
    
}
