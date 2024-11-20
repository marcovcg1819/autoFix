package cl.usach.ms_catalogosv.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marcas")
@Data
public class MarcasEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marcas_sequence")
	@SequenceGenerator(name = "marcas_sequence", sequenceName = "marcas_sequence", allocationSize = 1)
	private long id;
	private String marca;

}
