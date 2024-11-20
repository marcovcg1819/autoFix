package cl.usach.ms_reportes.models;

import lombok.Data;

@Data
public class Reporte2Resp {
	
	private String tipoReparacion;
	private Integer cantidadMes1;
	private Float montoMes1;
	private Integer cantidadMes2;
	private Float montoMes2;
	private Float variacionMes2;
	private Float variacionMontoMes2;
	private Integer cantidadMes3;
	private Float montoMes3;
	private Float variacionMes3;
	private Float variacionMontoMes3;

}
