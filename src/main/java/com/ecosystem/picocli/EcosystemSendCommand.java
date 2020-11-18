package com.ecosystem.picocli;

import com.ecosystem.service.ProtobufBrokerCliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(description = "Command to send the content of a json file to the message broker (serialized as protobuf.)",
        name = "send")
@Component
public class EcosystemSendCommand implements Callable<String> {

    @Autowired
    private ProtobufBrokerCliService protobufBrokerCliService;


    @CommandLine.Option(names = {"-f", "--file"})
    private String filename;

    @Override
    public String call() throws Exception {
        return protobufBrokerCliService.sendToMessageBroker(filename).getFeedback();
    }
}
