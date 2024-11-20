package cl.usach.ms_reparaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.PreciosEntity;

@Repository
public interface PreciosRepository extends JpaRepository<PreciosEntity, Long>{
	
	PreciosEntity findByIdTipoVehiculo(Long id);

}
