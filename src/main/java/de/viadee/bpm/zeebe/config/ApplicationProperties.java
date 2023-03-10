package de.viadee.bpm.zeebe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("de.viadee.bpm.zeebe")
public class ApplicationProperties {

    @NestedConfigurationProperty
    private ZeebeProperties client = new ZeebeProperties();

    @NestedConfigurationProperty
    private ConnectorProperties connector = new ConnectorProperties();

    public ZeebeProperties getClient() {
        return client;
    }

    public void setClient(final ZeebeProperties client) {
        this.client = client;
    }

    public ConnectorProperties getConnector() {
        return connector;
    }

    public void setConnector(final ConnectorProperties connector) {
        this.connector = connector;
    }
}
