package com.cuentas.mscuentas.infrastructure.controller;

import com.cuentas.mscuentas.infrastructure.controller.dto.MovimientoRequest;
import com.cuentas.mscuentas.infrastructure.controller.dto.MovimientoResponse;
import com.cuentas.mscuentas.domain.model.Movimiento;
import com.cuentas.mscuentas.application.MovimientoService;
import com.cuentas.mscuentas.infrastructure.mapper.MovimientoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;
    private final MovimientoMapper movimientoMapper;

    public MovimientoController(MovimientoService movimientoService, MovimientoMapper movimientoMapper) {
        this.movimientoService = movimientoService;
        this.movimientoMapper = movimientoMapper;
    }

    @PostMapping("/cuenta/{cuentaId}")
    public ResponseEntity<MovimientoResponse> registrar(@PathVariable Long cuentaId,
                                                        @RequestBody MovimientoRequest req) {
        Movimiento mov = new Movimiento();
        mov.setTipoMovimiento(req.tipoMovimiento());
        mov.setValor(req.valor());
        Movimiento registrado = movimientoService.registrar(cuentaId, mov);
        return ResponseEntity.ok(movimientoMapper.toResponse(registrado));
    }

    @GetMapping("/cuenta/{cuentaId}")
    public List<MovimientoResponse> listarPorCuenta(@PathVariable Long cuentaId) {
        return movimientoService.listarPorCuenta(cuentaId).stream().map(movimientoMapper::toResponse).toList();
    }
}
