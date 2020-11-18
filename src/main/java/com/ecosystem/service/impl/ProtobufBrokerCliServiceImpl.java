package com.ecosystem.service.impl;

import com.ecosystem.protobuf.PersonProtobuf;
import com.ecosystem.protobuf.mapper.ProtobufMapper;
import com.ecosystem.rabbitmq.RabbitMqClient;
import com.ecosystem.service.ProtobufBrokerCliService;
import com.ecosystem.service.exception.ProtobufBrokerCliException;
import com.ecosystem.service.response.GetResponse;
import com.ecosystem.service.response.SendToMessageBrokerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

@Service
public class ProtobufBrokerCliServiceImpl extends ProtobufBrokerCliServiceUtils implements ProtobufBrokerCliService {


    @Autowired
    private ProtobufMapper protobufMapper;

    @Autowired
    private RabbitMqClient rabbitMqClient;

    @Override
    public SendToMessageBrokerResponse sendToMessageBroker(String fileName){
        SendToMessageBrokerResponse response = new SendToMessageBrokerResponse();
        String jsonString;
        try {
            File file = ResourceUtils.getFile(fileName);
            //Read File Content
            jsonString = new String(Files.readAllBytes(file.toPath()));
            PersonProtobuf.Person person = protobufMapper.toProtobuf(jsonString);
            rabbitMqClient.send(person);
        }catch (Exception e){
            handleException(e, response);
            return response;
        }
        response.setFeedback("OK");
        return response;
    }

    @Override
    public GetResponse get() {
        GetResponse response = new GetResponse();
        try {
            PersonProtobuf.Person person = rabbitMqClient.receive();
            if(person != null) {
                String jsonString = protobufMapper.toJson(person);
                response.setJsonContent(jsonString);
            }else{
                throw new ProtobufBrokerCliException();
            }
        }catch (Exception e){
            handleException(e, response);
            return response;
        }
        return response;
    }
}
