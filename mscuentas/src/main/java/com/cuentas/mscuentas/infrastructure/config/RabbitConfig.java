package com.cuentas.mscuentas.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_NAME = "cliente.exchange";
    public static final String QUEUE_NAME = "cliente.creado.queue";
    public static final String ROUTING_KEY = "cliente.creado";

    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue clienteQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue clienteQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(clienteQueue).to(clienteExchange).with(ROUTING_KEY);
    }
}
