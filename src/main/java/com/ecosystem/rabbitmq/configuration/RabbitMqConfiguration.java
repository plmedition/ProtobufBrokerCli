package com.ecosystem.rabbitmq.configuration;

import com.ecosystem.rabbitmq.converter.PersonProtobufMessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.default.queue:protobof_queue}")
    private String QUEUE_NAME;

    @Value("${rabbitmq.host:localhost}")
    private String HOST;

    @Value("${rabbitmq.port:5672}")
    private int PORT;

    @Value("${rabbitmq.username:guest}")
    private String USERNAME;

    @Value("${rabbitmq.password:guest}")
    private String PASSWORD;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUsername(USERNAME);
        cachingConnectionFactory.setPassword(PASSWORD);
        cachingConnectionFactory.setHost(HOST);
        cachingConnectionFactory.setPort(PORT);
        return cachingConnectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(new PersonProtobufMessageConverter());
        rabbitTemplate.setDefaultReceiveQueue(QUEUE_NAME);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }
}
