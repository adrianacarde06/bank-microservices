package com.cuentas.mscuentas.domain.exception;

public class CuentaInactivaException extends RuntimeException {
    public CuentaInactivaException(String mensaje) {
        super(mensaje);
    }
}
