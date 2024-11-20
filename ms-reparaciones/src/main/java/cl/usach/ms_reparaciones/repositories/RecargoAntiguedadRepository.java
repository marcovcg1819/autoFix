package cl.usach.ms_reparaciones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.RecargoAntiguedadEntity;

@Repository
public interface RecargoAntiguedadRepository extends JpaRepository<RecargoAntiguedadEntity, Long>{

	List<RecargoAntiguedadEntity> findByIdModelo(Long id_modelo);
}
