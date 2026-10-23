package com.cuentas.mscuentas.infrastructure.controller.dto;

import com.cuentas.mscuentas.domain.model.TipoMovimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoReporteDto(
        LocalDateTime fecha,
        TipoMovimiento tipoMovimiento,
        BigDecimal valor,
        BigDecimal saldoResultante
) {}
