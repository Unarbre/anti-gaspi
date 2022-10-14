package com.soat.anti_gaspi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sendgrid")
public class SendgridProperties {
    private String url;
    private String apiKey;

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
