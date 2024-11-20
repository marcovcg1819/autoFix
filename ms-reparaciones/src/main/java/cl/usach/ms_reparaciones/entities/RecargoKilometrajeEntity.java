package cl.usach.ms_reparaciones.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="recargo_kilometraje")
@Data
public class RecargoKilometrajeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recargo_kilometraje_sequence")
	@SequenceGenerator(name = "recargo_kilometraje_sequence", sequenceName = "recargo_kilometraje_sequence",  allocationSize=1)
	private Long id;
	private String intervalo;
	@Column(name = "id_modelo")
	private Long idModelo;
	private Long descuento_porcentaje;

}
