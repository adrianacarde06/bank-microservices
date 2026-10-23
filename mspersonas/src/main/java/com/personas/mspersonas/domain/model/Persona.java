package com.personas.mspersonas.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Persona {

    private Long id;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}
