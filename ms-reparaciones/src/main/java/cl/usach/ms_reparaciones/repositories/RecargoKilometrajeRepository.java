package cl.usach.ms_reparaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.RecargoKilometrajeEntity;

@Repository
public interface RecargoKilometrajeRepository extends JpaRepository<RecargoKilometrajeEntity, Long>{
	
	List<RecargoKilometrajeEntity> findByIdModelo(Long id_modelo);
}
