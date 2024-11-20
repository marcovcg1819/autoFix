package cl.usach.ms_reparaciones.models;

import lombok.Data;

@Data
public class RequestAplicarDescuentoBono {
	
	private Long idReparacion;
	private Long idBono;

}
