package cl.usach.ms_catalogosv.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipos")
@Data
public class TiposEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipos_sequence")
	@SequenceGenerator(name = "tipos_sequence", sequenceName = "tipos_sequence", allocationSize = 1)
	private long id;
	private String tipo;
}
