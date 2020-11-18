package com.ecosystem.service;

import com.ecosystem.service.response.GetResponse;
import com.ecosystem.service.response.SendToMessageBrokerResponse;

/**
 *  Main service that allows the CLI to send and get the json content
 */
public interface ProtobufBrokerCliService {

    /**
     * This method receives the path of the json file , serialize it to protobuf and send it to the message broker
     *
     * @param fileName path of the file that will be sent to the message broker
     * @return SendToMessageBrokerResponse with all the feedback of the process. It does not
     * matter if it everything went right or wrong
     */
    SendToMessageBrokerResponse sendToMessageBroker(String fileName);

    /**
     * This methods take the last message stored in the queue protobuf_queue [spring.rabbitmq.default.queue]
     * deserialize to Json and return it as a String
     *
     * @return GetResponse with all the feedback of the process. It does not
     *      * matter if it everything went right or wrong
     */
    GetResponse get();
}
