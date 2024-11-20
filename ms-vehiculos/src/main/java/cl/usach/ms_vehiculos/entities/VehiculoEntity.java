package cl.usach.ms_vehiculos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="vehiculo")
@Data
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehiculo_sequence")
	@SequenceGenerator(name = "vehiculo_sequence", sequenceName = "vehiculo_sequence",  allocationSize=1)
    private Long id;
    private String n_patente;
    private Integer id_marca;
    private Integer id_modelo;
    private Integer id_tipo_auto;
    private String anio_fabricacion;
    private Integer n_asientos;
    private Integer kilometraje;

}
