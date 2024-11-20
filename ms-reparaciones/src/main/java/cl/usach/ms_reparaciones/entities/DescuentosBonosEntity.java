package cl.usach.ms_reparaciones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="descuentos_por_bonos")
@Data
public class DescuentosBonosEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descuentos_por_bonos_sequence")
	@SequenceGenerator(name = "descuentos_por_bonos_sequence", sequenceName = "descuentos_por_bonos_sequence",  allocationSize=1)
	private Long id;
	private String marca;
	private Integer numero_bono;
	private Float dinero_bono;
}
