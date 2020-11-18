package com.ecosystem;

import com.ecosystem.picocli.EcosystemCommand;
import com.ecosystem.picocli.EcosystemGetCommand;
import com.ecosystem.picocli.EcosystemSendCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProtobufBrokerCliApplication implements CommandLineRunner {

    private EcosystemCommand ecosystemCommand;
    private EcosystemSendCommand ecosystemSendCommand;
    private EcosystemGetCommand ecosystemGetCommand;

    public static void main(String[] args) {
        SpringApplication.run(ProtobufBrokerCliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //commandLine.
    }

    public ProtobufBrokerCliApplication(EcosystemCommand ecosystemCommand,
                                        EcosystemSendCommand ecosystemSendCommand,
                                        EcosystemGetCommand ecosystemGetCommand) {
        this.ecosystemCommand = ecosystemCommand;
        this.ecosystemSendCommand = ecosystemSendCommand;
        this.ecosystemGetCommand = ecosystemGetCommand;
    }
}
