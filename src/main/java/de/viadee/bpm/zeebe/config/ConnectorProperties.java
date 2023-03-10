package de.viadee.bpm.zeebe.config;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

public class ConnectorProperties {

    @NestedConfigurationProperty
    private OpenAiProperties openAi = new OpenAiProperties();

    public OpenAiProperties getOpenAi() {
        return openAi;
    }

    public void setOpenAi(final OpenAiProperties openAi) {
        this.openAi = openAi;
    }
}
