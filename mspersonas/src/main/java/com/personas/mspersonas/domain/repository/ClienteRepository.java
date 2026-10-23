package com.personas.mspersonas.domain.repository;

import com.personas.mspersonas.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Cliente guardar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
    Optional<Cliente> buscarPorIdentificacion(String identificacion);
    List<Cliente> listarTodos();
    void eliminar(Long id);
}
