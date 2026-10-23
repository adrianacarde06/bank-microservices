package com.cuentas.mscuentas.application;

import com.cuentas.mscuentas.domain.exception.CuentaInactivaException;
import com.cuentas.mscuentas.domain.exception.SaldoInsuficienteException;
import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.domain.model.Movimiento;
import com.cuentas.mscuentas.domain.repository.CuentaRepository;
import com.cuentas.mscuentas.domain.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public MovimientoService(CuentaRepository cuentaRepository,
                             MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public Movimiento registrar(Long cuentaId, Movimiento movimiento) {
        Cuenta cuenta = cuentaRepository.buscarPorId(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + cuentaId));

        if (!cuenta.tieneSaldoSuficiente(movimiento.getValor())) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        if (cuenta.getEstado() == null || !cuenta.getEstado()) {
            throw new CuentaInactivaException("La cuenta con número " + cuenta.getNumeroCuenta() + " está inactiva. No se pueden registrar movimientos.");
        }

        if (movimiento.getTipoMovimiento().esRetiro() && movimiento.getValor().compareTo(BigDecimal.ZERO) > 0) {
            movimiento.setValor(movimiento.getValor().negate());
        }

        if (!movimiento.getTipoMovimiento().esRetiro() && movimiento.getValor().compareTo(BigDecimal.ZERO) < 0) {
            movimiento.setValor(movimiento.getValor().abs());
        }

        cuenta.aplicarMovimiento(movimiento.getValor());
        cuentaRepository.guardar(cuenta);

        movimiento.setCuentaId(cuentaId);
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setSaldoResultante(cuenta.getSaldoActual());

        return movimientoRepository.guardar(movimiento);
    }

    public List<Movimiento> listarPorCuenta(Long cuentaId) {
        return movimientoRepository.listarPorCuenta(cuentaId);
    }
}

