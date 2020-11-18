package com.ecosystem.rabbitmq;

import com.ecosystem.protobuf.PersonProtobuf;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration()
@SpringBootTest
class RabbitMqClientTest {

    @Autowired
    private RabbitMqClient rabbitMqClient;

    @Test
    public void testSendPersonAndGetMessage() {
        PersonProtobuf.Person person = PersonProtobuf.Person.newBuilder().setName("Pablo").setId(1).setEmail("pablo@mail.com").build();
        rabbitMqClient.send(person);
        person = rabbitMqClient.receive();
        assertNotNull(person);
    }
}