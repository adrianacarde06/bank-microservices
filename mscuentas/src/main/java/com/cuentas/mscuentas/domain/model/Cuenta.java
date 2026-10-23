package com.cuentas.mscuentas.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Cuenta {

    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;
    private Long clienteId;

    public boolean tieneSaldoSuficiente(BigDecimal valor) {
        return this.saldoActual.add(valor).compareTo(BigDecimal.ZERO) >= 0;
    }

    public void aplicarMovimiento(BigDecimal valor) {
        this.saldoActual = this.saldoActual.add(valor);
    }

}
