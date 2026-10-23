package com.cuentas.mscuentas.infrastructure.controller.dto;

import com.cuentas.mscuentas.domain.model.TipoMovimiento;

import java.math.BigDecimal;

public record MovimientoRequest(TipoMovimiento tipoMovimiento, BigDecimal valor) {}
