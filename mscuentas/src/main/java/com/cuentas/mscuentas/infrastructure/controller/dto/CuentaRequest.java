package com.cuentas.mscuentas.infrastructure.controller.dto;

import java.math.BigDecimal;

public record CuentaRequest(String numeroCuenta, String tipoCuenta,
                            BigDecimal saldoInicial, Boolean estado, Long clienteId) {}
