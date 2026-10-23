package com.personas.mspersonas.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClienteEntity extends PersonaEntity {

    @Column(nullable = false)
    private String contrasena;
    private Boolean estado;
}
