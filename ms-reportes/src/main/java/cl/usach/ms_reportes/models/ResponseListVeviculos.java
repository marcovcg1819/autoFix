package cl.usach.ms_reportes.models;

import lombok.Data;

@Data
public class ResponseListVeviculos {
	
	private Long id;
	private String marca;
	private String modelos;
	private String tipo;
	private String anio_fabricacion;
	private Long id_marca;
	private Long id_modelo;
	private Long id_tipo_auto;
	private Integer kilometraje;
	private Integer n_asientos;
	private String n_patente;
}
