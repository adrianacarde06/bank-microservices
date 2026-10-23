package com.cuentas.mscuentas.domain.model;

public enum TipoMovimiento {
    DEPOSITO,
    RETIRO;

    // Metodo de utilidad para saber si el movimiento resta o suma saldo
    public boolean esRetiro() {
        return this == RETIRO;
    }
}
