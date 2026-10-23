package com.cuentas.mscuentas.infrastructure.persistence;

import com.cuentas.mscuentas.infrastructure.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaId(Long cuentaId);

    @Query("SELECT m FROM MovimientoEntity m WHERE m.cuentaId = :cuentaId AND m.fecha BETWEEN :inicio AND :fin")
    List<MovimientoEntity> findByCuentaIdAndFechaBetween(
            @Param("cuentaId") Long cuentaId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}
