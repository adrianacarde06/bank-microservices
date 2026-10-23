package com.cuentas.mscuentas.infrastructure.persistence;

import com.cuentas.mscuentas.infrastructure.mapper.CuentaMapper;
import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {

    private final JpaCuentaRepository jpa;
    private final CuentaMapper mapper;

    public CuentaRepositoryImpl(JpaCuentaRepository jpa, CuentaMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Cuenta guardar(Cuenta c) {
        return mapper.toDomain(jpa.save(mapper.toEntity(c)));
    }

    @Override
    public Optional<Cuenta> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cuenta> listarTodas() {
        return jpa.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Cuenta> listarPorCliente(Long clienteId) {
        return jpa.findByClienteId(clienteId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
