package cl.usach.ms_reparaciones.models;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class RequestReparacionHoraFechaSalida {
	
	private Date fecha_salida;
	private Time hora_salida;

}
