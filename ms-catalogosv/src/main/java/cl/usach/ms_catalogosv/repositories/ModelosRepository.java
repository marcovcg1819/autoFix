package cl.usach.ms_catalogosv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.usach.ms_catalogosv.entities.ModelosEntity;

@Repository
public interface ModelosRepository extends JpaRepository<ModelosEntity, Long>{

}
