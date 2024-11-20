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
@Table(name="precios")
@Data
public class PreciosEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "precios_sequence")
	@SequenceGenerator(name = "precios_sequence", sequenceName = "precios_sequence",  allocationSize=1)
    private Long id;

	@Column(name = "id_tipo_reparacion")	
    private Long idTipoReparacion;
	@Column(name = "id_tipo_vehiculo")
    private Long idTipoVehiculo;
    private Float precio;
}
