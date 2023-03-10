package de.viadee.bpm.zeebe.connector;

import de.viadee.bpm.zeebe.service.OpenAiAccess;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OpenAiConnector implements OutboundConnectorFunction {
    private static final Logger log = LoggerFactory.getLogger(OpenAiConnector.class);

    private final OpenAiAccess openAiAccess;

    public OpenAiConnector(final OpenAiAccess openAiAccess) {
        this.openAiAccess = openAiAccess;
    }

    @Override
    public Object execute(final OutboundConnectorContext context) {
        log.info("execute open-ai-connector");

        var instructions = context.getVariablesAsType(OpenAiInstructions.class);

        if (instructions.isSentimentAnalysis()) {
            var result = openAiAccess.performSentimentAnalysis(instructions.getInput());
            log.info("sentiment result: {}", result);
            return result;

        } else {
            var result =  openAiAccess.determineCustomerConcerns(instructions.getInput());
            log.info("customer concern result: {}", result);
            return result;
        }
    }
}
