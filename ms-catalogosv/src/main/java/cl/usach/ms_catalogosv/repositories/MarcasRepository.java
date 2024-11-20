package cl.usach.ms_catalogosv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_catalogosv.entities.MarcasEntity;

@Repository
public interface MarcasRepository extends JpaRepository<MarcasEntity, Long>{

}
