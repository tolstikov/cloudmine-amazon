package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.ConfigurationRecorder;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationRecordersResponse extends AmazonResponse {

    private List<ConfigurationRecorder> configurationRecorders;

    public List<ConfigurationRecorder> getConfigurationRecorders() {
        return configurationRecorders;
    }

    public void setConfigurationRecorders(final List<ConfigurationRecorder> configurationRecorders) {
        this.configurationRecorders = configurationRecorders;
    }
}
