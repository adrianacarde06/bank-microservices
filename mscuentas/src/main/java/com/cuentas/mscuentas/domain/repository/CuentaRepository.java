package com.cuentas.mscuentas.domain.repository;

import com.cuentas.mscuentas.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository {

    Cuenta guardar(Cuenta cuenta);
    Optional<Cuenta> buscarPorId(Long id);
    List<Cuenta> listarTodas();
    List<Cuenta> listarPorCliente(Long clienteId);
}
