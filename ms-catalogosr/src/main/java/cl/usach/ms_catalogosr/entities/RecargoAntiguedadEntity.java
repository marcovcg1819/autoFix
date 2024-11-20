package cl.usach.ms_catalogosr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="recargo_antiguedad")
@Data
public class RecargoAntiguedadEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recargo_antiguedad_sequence")
	@SequenceGenerator(name = "recargo_antiguedad_sequence", sequenceName = "recargo_antiguedad_sequence",  allocationSize=1)
	private Long id;
	private String intervalo_anios;
	private Long id_modelo;
	private Long descuento_porcentaje;
}
