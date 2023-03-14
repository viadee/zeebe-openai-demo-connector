package de.viadee.bpm.zeebe.connector;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAiInstructions {
    @JsonProperty("openai_input")
    private String input;

    @JsonProperty("openai_function")
    private String function;

    public String getInput() {
        return input;
    }

    public void setInput(final String input) {
        this.input = input;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(final String function) {
        this.function = function;
    }

    @JsonIgnore
    public boolean isSentimentAnalysis() {
        return "sentiment_analysis".equals(this.function);
    }
}
