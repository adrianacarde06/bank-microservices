package com.personas.mspersonas.infrastructure.persistence.entity;

import com.personas.mspersonas.domain.model.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer edad;

    @Column(unique = true)
    private String identificacion;

    private String direccion;
    private String telefono;
}
