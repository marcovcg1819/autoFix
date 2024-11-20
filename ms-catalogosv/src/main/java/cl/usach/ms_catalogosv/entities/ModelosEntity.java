package cl.usach.ms_catalogosv.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "modelos")
@Data
public class ModelosEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modelos_sequence")
	@SequenceGenerator(name = "modelos_sequence", sequenceName = "modelos_sequence", allocationSize = 1)
	private long id;
	private String modelos;
}
