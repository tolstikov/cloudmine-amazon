package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.ConfigurationRecorderStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationRecordersStatusResponse extends AmazonResponse {

    private List<ConfigurationRecorderStatus> configurationRecordersStatus;

    public List<ConfigurationRecorderStatus> getConfigurationRecordersStatus() {
        return configurationRecordersStatus;
    }

    public void setConfigurationRecordersStatus(final List<ConfigurationRecorderStatus> configurationRecordersStatus) {
        this.configurationRecordersStatus = configurationRecordersStatus;
    }
}
