package cl.usach.ms_catalogosr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="reparacion_descuentos")
@Data
public class ReparacionDescuentosEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_descuentos_sequence")
	@SequenceGenerator(name = "reparacion_descuentos_sequence", sequenceName = "reparacion_descuentos_sequence",  allocationSize=1)
	private Long id;
	private String numero_reparaciones_ultimo_anio;
	private Long id_tipo_vehiculo;
	private Long descuento_porcentaje;
}
