package de.viadee.bpm.zeebe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("connector")
public class OpenAiProperties {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
