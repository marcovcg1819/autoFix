package cl.usach.ms_catalogosr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_catalogosr.entities.ReparacionSettingsEntity;

@Repository
public interface ReparacionSettingsRepository extends JpaRepository<ReparacionSettingsEntity, Long>{

}
