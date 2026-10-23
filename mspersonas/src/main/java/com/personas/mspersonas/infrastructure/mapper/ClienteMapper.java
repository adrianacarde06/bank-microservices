package com.personas.mspersonas.infrastructure.mapper;

import com.personas.mspersonas.domain.model.Cliente;
import com.personas.mspersonas.infrastructure.dto.ClienteRequest;
import com.personas.mspersonas.infrastructure.dto.ClienteResponse;
import com.personas.mspersonas.infrastructure.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    // De Entidad JPA a Dominio
    Cliente toDomain(ClienteEntity entity);

    // De Dominio a Entidad JPA
    ClienteEntity toEntity(Cliente domain);

    // De DTO Request a Dominio
    Cliente toDomain(ClienteRequest request);

    // De Dominio a DTO Response
    ClienteResponse toResponse(Cliente domain);
}
