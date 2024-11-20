package cl.usach.ms_reparaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.ReparacionSettingsEntity;

@Repository
public interface ReparacionSettingsRepository extends JpaRepository<ReparacionSettingsEntity, Long>{
	
	ReparacionSettingsEntity findByNombre(String nombre);

}
