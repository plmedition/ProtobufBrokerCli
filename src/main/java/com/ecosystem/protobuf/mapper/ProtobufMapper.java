package com.ecosystem.protobuf.mapper;

import com.ecosystem.protobuf.PersonProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.springframework.stereotype.Component;

@Component
public class ProtobufMapper {

    public PersonProtobuf.Person toProtobuf(String jsonString) throws InvalidProtocolBufferException {
        PersonProtobuf.Person.Builder personBuilder = PersonProtobuf.Person.newBuilder();
        JsonFormat.parser().merge(jsonString, personBuilder);
        return personBuilder.build();
    }

    public String toJson(PersonProtobuf.Person person) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(person);
    }
}
