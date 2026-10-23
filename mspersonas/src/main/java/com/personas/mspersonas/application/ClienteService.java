package com.personas.mspersonas.application;

import com.personas.mspersonas.domain.model.Cliente;
import com.personas.mspersonas.domain.ports.PasswordEncoderPort;
import com.personas.mspersonas.domain.repository.ClienteRepository;
import com.personas.mspersonas.infrastructure.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordEncoderPort passwordEncoder;

    public ClienteService(ClienteRepository clienteRepository,
                          RabbitTemplate rabbitTemplate,
                          PasswordEncoderPort passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public Cliente crear(Cliente cliente) {
        Cliente guardado;
        String passwordEncriptada = passwordEncoder.encode(cliente.getContrasena());
        cliente.setContrasena(passwordEncriptada);
        try {
            guardado = clienteRepository.guardar(cliente);
        } catch (DataIntegrityViolationException ex){
            throw new RuntimeException("Cliente ya existe con Numero de Identificacion: "+cliente.getIdentificacion());

        }

        // Enviar mensaje asíncrono con el ID del cliente creado
        if(guardado.getId() != null)
            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE_NAME,
                    RabbitConfig.ROUTING_KEY,
                    guardado.getId()
            );
        return guardado;
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
    }

    public List<Cliente> listar() {
        return clienteRepository.listarTodos();
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        return clienteRepository.buscarPorIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con identificación: " + identificacion));
    }

    public Cliente actualizar(Long id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setGenero(datos.getGenero());
        existente.setEdad(datos.getEdad());
        existente.setDireccion(datos.getDireccion());
        existente.setTelefono(datos.getTelefono());
        existente.setEstado(datos.getEstado());
        if (datos.getContrasena() != null && !datos.getContrasena().isEmpty()) {
            existente.setContrasena(passwordEncoder.encode(datos.getContrasena()));
        }
        return clienteRepository.guardar(existente);
    }

    public Cliente actualizarParcial(Long id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        if (datos.getNombre() != null) existente.setNombre(datos.getNombre());
        if (datos.getTelefono() != null) existente.setTelefono(datos.getTelefono());
        if (datos.getDireccion() != null) existente.setDireccion(datos.getDireccion());
        if (datos.getEstado() != null) existente.setEstado(datos.getEstado());
        return clienteRepository.guardar(existente);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        clienteRepository.eliminar(id);
    }
}
