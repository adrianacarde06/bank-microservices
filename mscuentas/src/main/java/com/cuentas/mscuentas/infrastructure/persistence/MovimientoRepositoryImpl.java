package com.cuentas.mscuentas.infrastructure.persistence;

import com.cuentas.mscuentas.infrastructure.mapper.MovimientoMapper;
import com.cuentas.mscuentas.domain.model.Movimiento;
import com.cuentas.mscuentas.domain.repository.MovimientoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovimientoRepositoryImpl implements MovimientoRepository {

    private final JpaMovimientoRepository jpa;
    private final MovimientoMapper mapper;

    public MovimientoRepositoryImpl(JpaMovimientoRepository jpa, MovimientoMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Movimiento guardar(Movimiento m) {
        return mapper.toDomain(jpa.save(mapper.toEntity(m)));
    }

    @Override
    public List<Movimiento> listarPorCuenta(Long cuentaId) {
        return jpa.findByCuentaId(cuentaId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Movimiento> listarPorCuentaYRango(Long cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        return jpa.findByCuentaIdAndFechaBetween(cuentaId, inicio, fin)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
