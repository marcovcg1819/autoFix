package cl.usach.ms_reparaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.ReparacionDescuentosEntity;

@Repository
public interface ReparacionDescuentosRepository extends JpaRepository<ReparacionDescuentosEntity, Long>{
	
	List<ReparacionDescuentosEntity> findByIdTipoVehiculo(Long id_reparacion);

}
