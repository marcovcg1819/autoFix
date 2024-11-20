package cl.usach.ms_reportes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reportes.entities.TipoReparacionesEntity;

@Repository
public interface TipoReparacionesRepository extends JpaRepository<TipoReparacionesEntity, Long>{

}
