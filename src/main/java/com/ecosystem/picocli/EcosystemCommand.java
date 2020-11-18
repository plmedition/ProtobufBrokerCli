package com.ecosystem.picocli;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@CommandLine.Command(name="ecosystem",
        subcommands = {
        EcosystemSendCommand.class,
        EcosystemGetCommand.class
})
@Component
public class EcosystemCommand implements Runnable {

    @Override
    public void run() {

    }
}
