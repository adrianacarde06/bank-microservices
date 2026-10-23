package com.personas.mspersonas.infrastructure.persistence;

import com.personas.mspersonas.infrastructure.mapper.ClienteMapper;
import com.personas.mspersonas.infrastructure.persistence.entity.ClienteEntity;
import com.personas.mspersonas.domain.model.Cliente;
import com.personas.mspersonas.domain.repository.ClienteRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JpaClienteRepository jpa;
    private final ClienteMapper mapper;

    public ClienteRepositoryImpl(JpaClienteRepository jpa, ClienteMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente);
        ClienteEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorIdentificacion(String identificacion) {
        return jpa.findByIdentificacion(identificacion).map(mapper::toDomain);
    }

    @Override
    public List<Cliente> listarTodos() {
        return jpa.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

}
