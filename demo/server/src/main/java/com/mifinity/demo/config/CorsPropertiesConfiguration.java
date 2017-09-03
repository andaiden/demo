package com.mifinity.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by andrea.schembri on 02/09/2017.
 */
@ConfigurationProperties(prefix="cors")
public class CorsPropertiesConfiguration {

    private List<String> allowedHeaders;
    private List<String> allowedMethods;
    private List<String> allowedOrigins;

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(final List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(final List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(final List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
