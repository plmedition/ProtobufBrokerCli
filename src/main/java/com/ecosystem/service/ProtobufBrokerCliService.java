package com.ecosystem.service;

import com.ecosystem.service.response.GetResponse;
import com.ecosystem.service.response.SendToMessageBrokerResponse;

public interface ProtobufBrokerCliService {

    SendToMessageBrokerResponse sendToMessageBroker(String fileName)throws Exception ;

    GetResponse get() throws Exception ;
}
