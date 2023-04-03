package de.viadee.bpm.zeebe.config;

import io.camunda.zeebe.client.CredentialsProvider;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Configuration
public class ZeebeConfig {

    private final ZeebeProperties zeebeClient;
    private final String zeebeUrl = "zeebe.camunda.io";

    public ZeebeConfig(final ApplicationProperties properties) {
        this.zeebeClient = properties.getClient();
    }

    @Bean
    public ZeebeClient zeebeClient() {
        return ZeebeClient.newClientBuilder()
                          .credentialsProvider(credentials())
                          .gatewayAddress(format("%s.%s.%s:%s",
                                  zeebeClient.getClusterId(),
                                  zeebeClient.getRegion(),
                                  zeebeUrl, 443))
                          .build();
    }

    private CredentialsProvider credentials() {
        return CredentialsProvider.newCredentialsProviderBuilder()
                                  .audience(format("%s.%s.%s",
                                          zeebeClient.getClusterId(),
                                          zeebeClient.getRegion(),
                                          zeebeUrl))
                                  .clientId(zeebeClient.getClientId())
                                  .clientSecret(zeebeClient.getClientSecret())
                                  .build();
    }
}
