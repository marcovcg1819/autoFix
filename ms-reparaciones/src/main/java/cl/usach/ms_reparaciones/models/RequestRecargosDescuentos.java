package cl.usach.ms_reparaciones.models;

import lombok.Data;

@Data
public class RequestRecargosDescuentos {
	
	private Long idReparacion;
	private Long idTipoVehiculo;
	private Long idModelo;
	private Integer kilometraje;
	private Integer anioFabricacion;

}
