package com.personas.mspersonas.infrastructure.dto;

import com.personas.mspersonas.domain.model.Genero;

public record ClienteResponse(
        String nombre, Genero genero, Integer edad, String identificacion,
        String direccion, String telefono) {}
