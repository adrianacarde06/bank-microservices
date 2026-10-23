package com.cuentas.mscuentas.infrastructure.mapper;

import com.cuentas.mscuentas.domain.model.Movimiento;
import com.cuentas.mscuentas.infrastructure.controller.dto.MovimientoRequest;
import com.cuentas.mscuentas.infrastructure.controller.dto.MovimientoResponse;
import com.cuentas.mscuentas.infrastructure.persistence.entity.MovimientoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {

    Movimiento toDomain(MovimientoEntity entity);

    MovimientoEntity toEntity(Movimiento domain);

    Movimiento toDomain(MovimientoRequest request);

    MovimientoResponse toResponse(Movimiento domain);
}
