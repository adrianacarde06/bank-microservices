package com.cuentas.mscuentas.application;

import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.domain.model.Movimiento;
import com.cuentas.mscuentas.domain.repository.CuentaRepository;
import com.cuentas.mscuentas.domain.repository.MovimientoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public ReporteService(CuentaRepository cuentaRepository,
                          MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    public List<ReporteDomain> generarReporte(Long clienteId, LocalDateTime inicio, LocalDateTime fin) {
        List<Cuenta> cuentas = cuentaRepository.listarPorCliente(clienteId);

        return cuentas.stream().map(cuenta -> {
            List<Movimiento> movimientos = movimientoRepository
                    .listarPorCuentaYRango(cuenta.getId(), inicio, fin);
            return new ReporteDomain(cuenta, movimientos);
        }).collect(Collectors.toList());
    }

    public record ReporteDomain(Cuenta cuenta, List<Movimiento> movimientos) {}
}
