package com.ecosystem.picocli;

import com.ecosystem.service.ProtobufBrokerCliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(description = "Command that allows the user to get a message from the broker.",
                    name = "get")
@Component
public class EcosystemGetCommand implements Callable<String> {

    @Autowired
    private ProtobufBrokerCliService protobufBrokerCliService;

    @Override
    public String call() throws Exception {
        return protobufBrokerCliService.get().getJsonContent();
    }
}
