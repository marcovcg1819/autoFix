package cl.usach.ms_reportes.models;

import java.sql.Time;
import java.util.Date;

public interface ListaReparacion {
	
	Long getId();
	Date getFecha_ingreso();
	Time getHora_ingreso();
	Float getMonto_total_reparaciones();
	Float getMonto_total_recargos();
	Float getMonto_iva();
	Float getTotal();
	Date getFecha_salida();
	Time getHora_salida();
	Date getFecha_entrega_cliente();
	Time getHora_entrega_cliente();
	String getPatente();
	Float getMonto_reparacion();
	Date getFecha_reparacion();
	Time getHora_reparacion();
	String getModelos();
	
}
