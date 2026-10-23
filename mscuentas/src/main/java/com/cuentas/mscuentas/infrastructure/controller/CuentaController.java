package com.cuentas.mscuentas.infrastructure.controller;

import com.cuentas.mscuentas.infrastructure.controller.dto.CuentaRequest;
import com.cuentas.mscuentas.infrastructure.controller.dto.CuentaResponse;
import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.application.CuentaService;
import com.cuentas.mscuentas.infrastructure.mapper.CuentaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;
    private final CuentaMapper cuentaMapper;

    public CuentaController(CuentaService cuentaService, CuentaMapper cuentaMapper) {
        this.cuentaService = cuentaService;
        this.cuentaMapper = cuentaMapper;
    }


    @PostMapping
    public ResponseEntity<CuentaResponse> crear(@RequestBody CuentaRequest req) {
        Cuenta creada = cuentaService.crear(cuentaMapper.toDomain(req));
        return ResponseEntity.ok(cuentaMapper.toResponse(creada));
    }

    @PutMapping("/{id}")
    public CuentaResponse actualizar(@PathVariable Long id, @RequestBody CuentaRequest req) {
        return cuentaMapper.toResponse(cuentaService.actualizar(id, cuentaMapper.toDomain(req)));
    }

    @GetMapping
    public List<CuentaResponse> listar() {
        return cuentaService.listar().stream().map(cuentaMapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public CuentaResponse buscar(@PathVariable Long id) {
        return cuentaMapper.toResponse(cuentaService.buscarPorId(id));
    }
}
