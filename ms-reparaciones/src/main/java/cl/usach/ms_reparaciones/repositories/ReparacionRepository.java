package cl.usach.ms_reparaciones.repositories;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reparaciones.entities.ReparacionEntity;
import cl.usach.ms_reparaciones.models.Reporte2Res;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM reparacion WHERE n_patente = :patente", nativeQuery = true)
    Integer findByPatente(@Param("patente") String patente);
    
    
    @Query(value = "SELECT r FROM ReparacionEntity r WHERE fecha_salida BETWEEN :fecha1 AND :fecha2")
    List<ReparacionEntity> getByMonth(@Param("fecha1") Timestamp fecha1, @Param("fecha2") Timestamp fecha2);
    
    @Query(value = "SELECT count(r.id) FROM reparacion r INNER JOIN reparacion_detalle d on r.id = d.id_reparacion WHERE r.fecha_ingreso BETWEEN :fecha1 AND :fecha2", nativeQuery = true)
    Long countReparaciones(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
    
    
    @Query(value = "select distinct t.tipo, count(r.id) as countmesuno, ((100/count(r.id))*(select count(a.id) countmesdos from reparacion a where a.tipo_reparacion = r.tipo_reparacion and a.fecha_sal between :fecha21 and :fecha22)) vardos, (select count(a.id) countmesdos from reparacion a where a.tipo_reparacion = r.tipo_reparacion and a.fecha_sal between :fecha21 and :fecha22), ((100/count(r.id))*(select count(b.id) countmestres from reparacion b where b.tipo_reparacion = r.tipo_reparacion and b.fecha_sal between :fecha31 and :fecha32)) vartres, (select count(b.id) countmestres from reparacion b where b.tipo_reparacion = r.tipo_reparacion and b.fecha_sal between :fecha31 and :fecha32) from reparacion r inner join tipo_reparaciones t on r.tipo_reparacion = t.id where r.fecha_sal between :fecha11 and :fecha12 group by t.tipo, r.tipo_reparacion", nativeQuery = true)
    Collection<Reporte2Res> getReport2(@Param("fecha11") Timestamp fecha11, @Param("fecha12") Timestamp fecha12, @Param("fecha21") Timestamp fecha21, @Param("fecha22") Timestamp fecha22,@Param("fecha31") Timestamp fecha31, @Param("fecha32") Timestamp fecha32);
}
