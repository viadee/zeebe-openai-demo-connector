package de.viadee.bpm.zeebe.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OpenAiAccess {
    private static final Logger log = LoggerFactory.getLogger(OpenAiAccess.class);

    private static final String OPEN_AI_MODEL = "text-davinci-003";

    private static final String PROMPT_SENTIMENT_ANALYSIS = """
            Klassifiziere das Sentiment in dieser Aussage:
            "%s"
            Sentiment:""";

    private static final String PROMPT_CUSTOMER_CONCERN = """
            Was ist das Kundenanliegen?
            "%s"
            Kundenanliegen:""";

    private final OpenAiService openAiService;

    public OpenAiAccess(final OpenAiService openAiService) {
        this.openAiService = openAiService;
    }


    public String performSentimentAnalysis(final String text) {
        log.info("sentiment analysis, text: {}", text);
        var prompt = PROMPT_SENTIMENT_ANALYSIS.formatted(text);
        return callOpenAiCompletion(prompt);
    }

    public String determineCustomerConcerns(final String text) {
        log.info("customer concerns, text: {}", text);
        var prompt = PROMPT_CUSTOMER_CONCERN.formatted(text);
        return callOpenAiCompletion(prompt);
    }

    private String callOpenAiCompletion(final String prompt) {
        var request = buildRequest(prompt);
        var completion = openAiService.createCompletion(request);
        var firstChoice = completion.getChoices().get(0);
        log.info("open-ai prompt: \n{}", prompt);
        return firstChoice.getText().trim().toLowerCase();
    }

    private CompletionRequest buildRequest(final String text) {
        return CompletionRequest.builder()
                                .prompt(text)
                                .model(OPEN_AI_MODEL)
                                .temperature(0d)
                                .maxTokens(50)
                                .build();
    }
}
