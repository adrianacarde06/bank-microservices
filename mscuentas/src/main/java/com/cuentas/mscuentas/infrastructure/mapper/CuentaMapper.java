package com.cuentas.mscuentas.infrastructure.mapper;

import com.cuentas.mscuentas.domain.model.Cuenta;
import com.cuentas.mscuentas.infrastructure.controller.dto.CuentaRequest;
import com.cuentas.mscuentas.infrastructure.controller.dto.CuentaResponse;
import com.cuentas.mscuentas.infrastructure.persistence.entity.CuentaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    Cuenta toDomain(CuentaEntity entity);

    CuentaEntity toEntity(Cuenta domain);

    Cuenta toDomain(CuentaRequest request);

    CuentaResponse toResponse(Cuenta domain);
}
