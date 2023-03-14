package de.viadee.bpm.zeebe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private final ApplicationProperties applicationProperties;

    public ApplicationConfig(final ApplicationProperties properties) {
        this.applicationProperties = properties;
    }

    @Bean
    public OpenAiProperties openAiConfigurationProperties() {
        return applicationProperties.getConnector().getOpenAi();
    }


}
