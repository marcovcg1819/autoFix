package cl.usach.ms_catalogosr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="reparacion_incluye")
@Data
public class ReparacionIncluyeEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_incluye_sequence")
	@SequenceGenerator(name = "reparacion_incluye_sequence", sequenceName = "reparacion_incluye_sequence",  allocationSize=1)
	private Long id;
	private Integer id_tipo_reparacion;
	private String reparacion;
}
