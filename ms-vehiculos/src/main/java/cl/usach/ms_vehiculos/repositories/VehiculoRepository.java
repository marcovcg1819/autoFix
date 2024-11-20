package cl.usach.ms_vehiculos.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.usach.ms_vehiculos.entities.VehiculoEntity;
import cl.usach.ms_vehiculos.modelos.ResponseListVeviculos;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {
	
	@Query(value = "SELECT v.id, v.n_patente, v.id_marca, m.marca, v.id_modelo, mo.modelos, v.id_tipo_auto, t.tipo, v.anio_fabricacion, v.n_asientos, v.kilometraje FROM vehiculo v INNER JOIN marcas m on m.id = v.id_marca INNER JOIN modelos mo on mo.id = v.id_modelo INNER JOIN tipos t on t.id = v.id_tipo_auto", nativeQuery = true)
	Collection<ResponseListVeviculos> getVehiculoList();
	
	@Query(value = "SELECT v.id, v.n_patente, v.id_marca, m.marca, v.id_modelo, mo.modelos, v.id_tipo_auto, t.tipo, v.anio_fabricacion, v.n_asientos, v.kilometraje FROM vehiculo v INNER JOIN marcas m on m.id = v.id_marca INNER JOIN modelos mo on mo.id = v.id_modelo INNER JOIN tipos t on t.id = v.id_tipo_auto WHERE v.n_patente = :patente", nativeQuery = true)
	ResponseListVeviculos getVehiculoByPatente(@Param("patente") String patente);
}
