package com.personas.mspersonas.application;

import com.personas.mspersonas.domain.model.Cliente;
import com.personas.mspersonas.domain.ports.PasswordEncoderPort;
import com.personas.mspersonas.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteDePrueba;

    @BeforeEach
    void setUp() {
        clienteDePrueba = new Cliente();
        clienteDePrueba.setNombre("Juan Perez");
        clienteDePrueba.setIdentificacion("123456789");
        clienteDePrueba.setContrasena("miPasswordSegura");
        clienteDePrueba.setEstado(true);
    }

    @Test
    @DisplayName("Debe crear un cliente, encriptar su contraseña y enviar evento a RabbitMQ")
    void testCrearCliente() {

        String passwordEncriptada = "$2a$10$abc123...";
        when(passwordEncoder.encode("miPasswordSegura")).thenReturn(passwordEncriptada);

        when(clienteRepository.guardar(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente c = invocation.getArgument(0);
            c.setId(1L);
            c.setClienteId(1L);
            return c;
        });

        Cliente resultado = clienteService.crear(clienteDePrueba);

        assertNotNull(resultado, "El cliente retornado no debe ser nulo");
        assertEquals(1L, resultado.getClienteId(), "El ID debe ser 1");
        assertEquals(passwordEncriptada, resultado.getContrasena(), "La contraseña debe estar encriptada");

        verify(clienteRepository, times(1)).guardar(any(Cliente.class));
        verify(rabbitTemplate, times(1)).convertAndSend(
                anyString(),
                anyString(),
                eq(1L)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción si el cliente no existe al buscar por ID")
    void testCrearClienteNoExiste() {

        Long idInexistente = 99L;
        when(clienteRepository.buscarPorId(idInexistente)).thenReturn(java.util.Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.buscarPorId(idInexistente);
        });

        assertEquals("Cliente no encontrado: 99", exception.getMessage());
        verify(clienteRepository, times(1)).buscarPorId(idInexistente);
    }
}
