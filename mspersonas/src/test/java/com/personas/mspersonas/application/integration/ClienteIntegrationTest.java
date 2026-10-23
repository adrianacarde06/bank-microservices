package com.personas.mspersonas.application.integration;

import com.personas.mspersonas.infrastructure.persistence.JpaClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaClienteRepository jpaClienteRepository;

    @BeforeEach
    void setUp() {
        jpaClienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Debe crear un cliente vía POST y luego consultarlo vía GET")
    void testCrearYConsultarCliente() throws Exception {

        String jsonCliente = """
                {
                    "nombre": "Ana Maria",
                    "genero": "FEMENINO",
                    "edad": 30,
                    "identificacion": "987654321",
                    "direccion": "Calle 123",
                    "telefono": "3001234567",
                    "contrasena": "miPassword123",
                    "estado": true
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana Maria"))
                .andExpect(jsonPath("$.identificacion").value("987654321"));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ana Maria"))
                .andExpect(jsonPath("$[0].identificacion").value("987654321"));
    }

    @Test
    @DisplayName("Debe retornar 404 si el cliente no existe")
    void testClienteNoExiste() throws Exception {
        mockMvc.perform(get("/clientes/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Cliente no encontrado: 999"));
    }
}
