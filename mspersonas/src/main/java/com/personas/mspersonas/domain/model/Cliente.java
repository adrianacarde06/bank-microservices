package com.personas.mspersonas.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends Persona {

    private Long clienteId;
    private String contrasena;
    private Boolean estado;
}
