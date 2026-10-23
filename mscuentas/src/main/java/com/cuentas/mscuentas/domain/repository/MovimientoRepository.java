package com.cuentas.mscuentas.domain.repository;

import com.cuentas.mscuentas.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository {

    Movimiento guardar(Movimiento movimiento);
    List<Movimiento> listarPorCuenta(Long cuentaId);
    List<Movimiento> listarPorCuentaYRango(Long cuentaId, LocalDateTime inicio, LocalDateTime fin);

}
