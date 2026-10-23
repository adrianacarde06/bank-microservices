package com.personas.mspersonas.infrastructure.dto;

import com.personas.mspersonas.domain.model.Genero;

public record ClienteRequest(
        String nombre, Genero genero, Integer edad,
        String identificacion, String direccion, String telefono,
        String contrasena, Boolean estado) {}
