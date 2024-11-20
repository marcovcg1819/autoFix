package cl.usach.ms_reportes.models;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class ResponseListReparaciones {
	
	private Long id;
	private Date fecha_ingreso;
	private Time hora_ingreso;
	private Float monto_total_reparaciones;
	private Float monto_total_recargos;
	private Float monto_iva;
	private Float total;
	private Date fecha_salida;
	private Time hora_salida;
	private Date fecha_entrega_cliente;
	private Time hora_entrega_cliente;
	private String patente;
	private Float monto_reparacion;
	private Date fecha_reparacion;
	private Time hora_reparacion;
	private String modelos;
	
	//vehiculo
	private String marca;
	private String tipo;
	private String anio_fabricacion;
	private Integer kilometraje;
	private Integer n_asientos;

}
