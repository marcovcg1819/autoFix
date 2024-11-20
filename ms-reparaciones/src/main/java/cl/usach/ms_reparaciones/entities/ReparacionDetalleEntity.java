package cl.usach.ms_reparaciones.entities;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="reparacion_detalle")
@Data
public class ReparacionDetalleEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_detalle_sequence")
	@SequenceGenerator(name = "reparacion_detalle_sequence", sequenceName = "reparacion_detalle_sequence",  allocationSize=1)
    private Long id;

	@Column(name = "id_reparacion")
    private Long idReparacion;
    private String patente;
    @Column(name = "id_precio")
    private Long idPrecio;
    private Date fecha_reparacion;
    private Time hora_reparacion;
    private Float monto_reparacion;
    private Long id_modelo;
}
