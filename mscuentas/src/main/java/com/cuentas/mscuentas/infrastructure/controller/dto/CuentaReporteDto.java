package com.cuentas.mscuentas.infrastructure.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public record CuentaReporteDto(
        String numeroCuenta,
        String tipoCuenta,
        BigDecimal saldoActual,
        List<MovimientoReporteDto> movimientos
) {}
