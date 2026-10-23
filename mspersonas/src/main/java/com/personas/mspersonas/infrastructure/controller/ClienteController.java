package com.personas.mspersonas.infrastructure.controller;

import com.personas.mspersonas.infrastructure.dto.ClienteRequest;
import com.personas.mspersonas.infrastructure.dto.ClienteResponse;
import com.personas.mspersonas.domain.model.Cliente;
import com.personas.mspersonas.application.ClienteService;
import com.personas.mspersonas.infrastructure.mapper.ClienteMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest req) {
        Cliente creado = clienteService.crear(clienteMapper.toDomain(req));
        return ResponseEntity.ok(clienteMapper.toResponse(creado));
    }

    @GetMapping
    public List<ClienteResponse> listar() {
        return clienteService.listar().stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscar(@PathVariable Long id) {
        return clienteMapper.toResponse(clienteService.buscarPorId(id));
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<ClienteResponse> buscarPorIdentificacion(@PathVariable String identificacion) {
        Cliente cliente = clienteService.buscarPorIdentificacion(identificacion);
        return ResponseEntity.ok(clienteMapper.toResponse(cliente));
    }

    @PutMapping("/{id}")
    public ClienteResponse actualizar(@PathVariable Long id, @RequestBody ClienteRequest req) {
        return clienteMapper.toResponse(clienteService.actualizar(id, clienteMapper.toDomain(req)));
    }

    @PatchMapping("/{id}")
    public ClienteResponse actualizarParcial(@PathVariable Long id, @RequestBody ClienteRequest req) {
        return clienteMapper.toResponse(clienteService.actualizarParcial(id, clienteMapper.toDomain(req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
