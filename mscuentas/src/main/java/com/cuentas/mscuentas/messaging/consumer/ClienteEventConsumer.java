package com.cuentas.mscuentas.messaging.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteEventConsumer {

    @RabbitListener(queues = "cliente.creado.queue")
    public void recibirClienteCreado(Long clienteId) {
        System.out.println("Cliente recibido asíncronamente con ID: " + clienteId);
    }
}
