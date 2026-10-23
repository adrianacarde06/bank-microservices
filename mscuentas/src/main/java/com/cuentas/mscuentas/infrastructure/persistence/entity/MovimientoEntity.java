package com.cuentas.mscuentas.infrastructure.persistence.entity;

import com.cuentas.mscuentas.domain.model.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cuentaId;
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    private BigDecimal valor;
    private BigDecimal saldoResultante;
}
