package com.cuentas.mscuentas.application;

import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.domain.repository.CuentaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta crear(Cuenta cuenta) {
        Cuenta guardado;
        cuenta.setSaldoActual(cuenta.getSaldoInicial());
        try {
            guardado = cuentaRepository.guardar(cuenta);
        } catch (DataIntegrityViolationException ex){
            throw new RuntimeException("Cuenta ya existe con Numero: "+cuenta.getNumeroCuenta());

        }
        return guardado;
    }

    public Cuenta buscarPorId(Long id) {
        return cuentaRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + id));
    }

    public List<Cuenta> listar() {
        return cuentaRepository.listarTodas();
    }

    public Cuenta actualizar(Long id, Cuenta datos) {
        Cuenta existente = buscarPorId(id);
        existente.setTipoCuenta(datos.getTipoCuenta());
        existente.setEstado(datos.getEstado());
        existente.setClienteId(datos.getClienteId());
        // No se permite modificar numeroCuenta ni saldoInicial
        return cuentaRepository.guardar(existente);
    }
}
