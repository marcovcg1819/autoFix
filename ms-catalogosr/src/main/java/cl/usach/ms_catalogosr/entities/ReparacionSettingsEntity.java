package cl.usach.ms_catalogosr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="reparacion_settings")
@Data
public class ReparacionSettingsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_settings_sequence")
	@SequenceGenerator(name = "reparacion_settings_sequence", sequenceName = "reparacion_settings_sequence",  allocationSize=1)
	private Long id;
	private String nombre;
	private String valor;
	private String descripcion;

}
