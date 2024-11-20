package cl.usach.ms_reportes.repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.usach.ms_reportes.entities.ReparacionEntity;
import cl.usach.ms_reportes.models.ListaReparacion;
import cl.usach.ms_reportes.models.Reporte1;
import cl.usach.ms_reportes.models.Reporte2;

@Repository
public interface ReparacionRepository extends JpaRepository<ReparacionEntity, Long> {

//    @Query(value = "SELECT COUNT(*) FROM reparacion WHERE n_patente = :patente", nativeQuery = true)
//    Integer findByPatente(@Param("patente") String patente);
//    
//    
//    @Query(value = "SELECT r FROM ReparacionEntity r WHERE fecha_salida BETWEEN :fecha1 AND :fecha2")
//    List<ReparacionEntity> getByMonth(@Param("fecha1") Timestamp fecha1, @Param("fecha2") Timestamp fecha2);
//    
//    @Query(value = "SELECT count(r.id) FROM reparacion r INNER JOIN reparacion_detalle d on r.id = d.id_reparacion WHERE r.fecha_ingreso BETWEEN SYMMETRIC :fecha1 AND :fecha2", nativeQuery = true)
//    Long countReparaciones(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
    
    
    @Query(value = "select tr.tipo, m.modelos, (select count(*) from reparacion_detalle rda inner join precios pre on pre.id =  rda.id_precio where rda.id_modelo = rd.id_modelo and pre.id_tipo_reparacion = p.id_tipo_reparacion group by rda.id_reparacion) as cantidad, (select sum(rda.monto_reparacion) from reparacion_detalle rda inner join precios pre on pre.id =  rda.id_precio where rda.id_modelo = rd.id_modelo and pre.id_tipo_reparacion = p.id_tipo_reparacion group by rda.id_reparacion) as monto from reparacion r inner join reparacion_detalle rd on r.id = rd.id_reparacion inner join precios p on rd.id_precio  = p.id inner join tipo_reparaciones tr on tr.id  = p.id_tipo_reparacion inner join reparacion_incluye ri on tr.id  = ri.id_tipo_reparacion inner join modelos m on m.id = rd.id_modelo WHERE r.fecha_salida  BETWEEN  :fecha1 AND :fecha2 group by tr.tipo, m.modelos, rd.id_modelo, p.id_tipo_reparacion order by tr.tipo, m.modelos", nativeQuery = true)
    Collection<Reporte1> getReporte1(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);
    
    @Query(value = "select tr.tipo, (select count(rda.id) from reparacion ra inner join reparacion_detalle rda on ra.id = rda.id_reparacion inner join precios pa on rda.id_precio  = pa.id inner join tipo_reparaciones tra on tra.id  = pa.id_tipo_reparacion WHERE ra.fecha_salida  BETWEEN :fecha1 AND :fecha2 and tra.tipo = tr.tipo) as cantidadmes, (select sum(rda.monto_reparacion) from reparacion ra inner join reparacion_detalle rda on ra.id = rda.id_reparacion inner join precios pa on rda.id_precio  = pa.id inner join tipo_reparaciones tra on tra.id  = pa.id_tipo_reparacion WHERE ra.fecha_salida  BETWEEN  :fecha1 AND :fecha2 and tra.tipo = tr.tipo) as montomes from reparacion r inner join reparacion_detalle rd on r.id = rd.id_reparacion inner join precios p on rd.id_precio  = p.id inner join tipo_reparaciones tr on tr.id  = p.id_tipo_reparacion inner join reparacion_incluye ri on tr.id  = ri.id_tipo_reparacion WHERE r.fecha_salida  BETWEEN  :fecha1 AND :fecha2 and tr.tipo = :tipo group by tr.tipo, p.id_tipo_reparacion order by tr.tipo", nativeQuery = true)
    Collection<Reporte2> getReporte2(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2, @Param("tipo") String tipo);
    
    @Query(value = "select r.id, r.fecha_ingreso, r.hora_ingreso, r.monto_total_reparaciones, r.monto_total_recargos, r.monto_iva, r.total, r.fecha_salida, r.hora_salida, r.fecha_entrega_cliente, r.hora_entrega_cliente, rd.patente, rd.monto_reparacion, rd.fecha_reparacion, rd.hora_reparacion, m.modelos from reparacion r inner join reparacion_detalle rd on r.id = rd.id_reparacion inner join modelos m on m.id = rd.id_modelo", nativeQuery = true)
    Collection<ListaReparacion> getLista();
}
