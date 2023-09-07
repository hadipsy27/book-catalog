package com.labs.catalog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloud")
public class CloudProperties {

    private String apiKey;

    private String apiSecretIde;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecretIde() {
        return apiSecretIde;
    }

    public void setApiSecretIde(String apiSecretIde) {
        this.apiSecretIde = apiSecretIde;
    }
}
