package com.cuentas.mscuentas.infrastructure.controller;

import com.cuentas.mscuentas.application.ReporteService;
import com.cuentas.mscuentas.infrastructure.controller.dto.CuentaReporteDto;
import com.cuentas.mscuentas.infrastructure.controller.dto.MovimientoReporteDto;
import com.cuentas.mscuentas.infrastructure.controller.dto.ReporteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteResponse> obtenerReporte(
            @RequestParam("fecha") String rangoFechas,
            @RequestParam("cliente") Long clienteId) {

        // Separar el rango de fechas (ej. "2026-10-01,2026-10-31")
        String[] fechas = rangoFechas.split(",");
        LocalDateTime inicio = LocalDate.parse(fechas[0]).atStartOfDay();
        LocalDateTime fin = LocalDate.parse(fechas[1]).atTime(LocalTime.MAX);

        List<ReporteService.ReporteDomain> datos = reporteService.generarReporte(clienteId, inicio, fin);

        List<CuentaReporteDto> cuentasDto = datos.stream().map(d -> new CuentaReporteDto(
                d.cuenta().getNumeroCuenta(),
                d.cuenta().getTipoCuenta(),
                d.cuenta().getSaldoActual(),
                d.movimientos().stream().map(m -> new MovimientoReporteDto(
                        m.getFecha(),
                        m.getTipoMovimiento(),
                        m.getValor(),
                        m.getSaldoResultante()
                )).toList()
        )).toList();

        return List.of(new ReporteResponse(clienteId, cuentasDto));
    }
}
