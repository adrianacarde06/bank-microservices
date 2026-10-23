package com.cuentas.mscuentas.infrastructure.persistence;

import com.cuentas.mscuentas.infrastructure.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCuentaRepository extends JpaRepository<CuentaEntity, Long> {

    List<CuentaEntity> findByClienteId(Long clienteId);
}
