package cl.usach.ms_reparaciones.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reparacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReparacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reparacion_sequence")
	@SequenceGenerator(name = "reparacion_sequence", sequenceName = "reparacion_sequence",  allocationSize=1)
    private Long id;

    private Date fecha_ingreso;
    private Time hora_ingreso;
    private Float monto_total_reparaciones;
    private Float monto_total_recargos;
    private Float monto_iva;
    private Float total;
    private Date fecha_salida;
    private Time hora_salida;
    private Date fecha_entrega_cliente;
    private Time hora_entrega_cliente;
}
