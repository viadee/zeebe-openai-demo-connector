package de.viadee.bpm.zeebe.config;

public class ZeebeProperties {

    private String clientId;
    private String clientSecret;
    private String clusterId;
    private String region;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(final String clusterId) {
        this.clusterId = clusterId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(final String region) {
        this.region = region;
    }
}
