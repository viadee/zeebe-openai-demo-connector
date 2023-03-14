package de.viadee.bpm.zeebe.config;

import com.theokanning.openai.service.OpenAiService;
import de.viadee.bpm.zeebe.connector.OpenAiConnector;
import de.viadee.bpm.zeebe.service.OpenAiAccess;
import io.camunda.connector.api.secret.SecretProvider;
import io.camunda.connector.runtime.util.outbound.ConnectorJobHandler;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAiConfig {

    private static final String ZEEBE_CONNECTOR_OPEN_AI_JOB_TYPE = "de.viadee.bpm.zeebe.connector:openai:1";
    private static final String ZEEBE_CONNECTOR_OPEN_AI_NAME = "viadee-openai-connector";
    private static final List<String> ZEEBE_CONNECTOR_OPEN_VARIABLES = List.of("openai_input", "openai_function");

    private final OpenAiProperties openAi;
    private final ZeebeClient zeebeClient;

    public OpenAiConfig(final OpenAiProperties openAi, final ZeebeClient zeebeClient) {
        this.openAi = openAi;
        this.zeebeClient = zeebeClient;
    }

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openAi.getToken());
    }

    @Bean
    public JobWorker jobWorker(final OpenAiAccess openAiAccess) {
        return zeebeClient.newWorker()
                          .jobType(ZEEBE_CONNECTOR_OPEN_AI_JOB_TYPE)
                          .handler(new ConnectorJobHandler(openAiConnectorFunction(openAiAccess), secretProvider()))
                          .fetchVariables(ZEEBE_CONNECTOR_OPEN_VARIABLES)
                          .name(ZEEBE_CONNECTOR_OPEN_AI_NAME)
                          .open();
    }

    private OpenAiConnector openAiConnectorFunction(final OpenAiAccess openAiAccess) {
        return new OpenAiConnector(openAiAccess);
    }

    private SecretProvider secretProvider() {
        return secret -> null;
    }

}
