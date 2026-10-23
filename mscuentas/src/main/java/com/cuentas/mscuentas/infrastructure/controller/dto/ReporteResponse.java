package com.cuentas.mscuentas.infrastructure.controller.dto;

import java.util.List;

public record ReporteResponse(
        Long clienteId,
        List<CuentaReporteDto> cuentas
) {}
