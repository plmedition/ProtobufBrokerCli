package com.ecosystem.rabbitmq.converter;

import com.ecosystem.protobuf.PersonProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class PersonProtobufMessageConverter extends AbstractMessageConverter {

    @Override
    protected Message createMessage(Object o, MessageProperties messageProperties) {
        PersonProtobuf.Person person = (PersonProtobuf.Person) o;
        return new Message(person.toByteArray(), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        try {
            return PersonProtobuf.Person.parseFrom(message.getBody());
        }catch (InvalidProtocolBufferException ipbe){
            throw new MessageConversionException(ipbe.getMessage());
        }
    }
}
