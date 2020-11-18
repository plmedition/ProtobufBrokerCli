package com.ecosystem.rabbitmq;

import com.ecosystem.protobuf.PersonProtobuf;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Implementation of the rabbitTemplate client that will access to the protobuf_queue to send
 * and get the messages stored on it
 */
@Component
public class RabbitMqClient {

    @Value("${spring.rabbitmq.default.queue:protobof_queue}")
    private String QUEUE_NAME;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(PersonProtobuf.Person person) throws AmqpException {
        rabbitTemplate.convertAndSend(QUEUE_NAME, person);
    }

    public PersonProtobuf.Person receive() throws AmqpException{
        return (PersonProtobuf.Person) rabbitTemplate.receiveAndConvert();
    }
}
