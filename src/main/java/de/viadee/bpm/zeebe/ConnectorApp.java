package de.viadee.bpm.zeebe;

import de.viadee.bpm.zeebe.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class ConnectorApp {

    public static void main(final String[] args) {
        SpringApplication.run(ConnectorApp.class, args);
    }
}
