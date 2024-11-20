package cl.usach.ms_reparaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.ReparacionDetalleEntity;

@Repository
public interface ReparacionDetalleRepository extends JpaRepository<ReparacionDetalleEntity, Long>{
	
	List<ReparacionDetalleEntity> findByIdReparacion(Long id_reparacion);

}
