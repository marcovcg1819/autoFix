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
@Table(name="reparacion_descuentos")
@Data
public class ReparacionDescuentosEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_descuentos_sequence")
	@SequenceGenerator(name = "reparacion_descuentos_sequence", sequenceName = "reparacion_descuentos_sequence",  allocationSize=1)
    private Long id;

	@Column(name = "numero_reparaciones_ultimo_anio")
    private String numeroReparacionesUltimoAnio;
	@Column(name = "id_tipo_vehiculo")
    private Long idTipoVehiculo;
    private Long descuento_porcentaje;

}
