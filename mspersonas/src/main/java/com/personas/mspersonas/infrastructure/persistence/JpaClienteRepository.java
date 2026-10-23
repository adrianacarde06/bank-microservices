package com.personas.mspersonas.infrastructure.persistence;

import com.personas.mspersonas.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByIdentificacion(String identificacion);
}
