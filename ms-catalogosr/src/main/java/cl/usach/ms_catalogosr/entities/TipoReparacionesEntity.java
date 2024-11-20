package cl.usach.ms_catalogosr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tipo_reparaciones")
@Data
public class TipoReparacionesEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_reparaciones_sequence")
	@SequenceGenerator(name = "tipo_reparaciones_sequence", sequenceName = "tipo_reparaciones_sequence",  allocationSize=1)
	private Long id;
	private String tipo;

}
