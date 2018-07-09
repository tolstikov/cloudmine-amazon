package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.Configuration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationsResponse extends AmazonResponse {

    private List<Configuration> configurations;

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(final List<Configuration> configurations) {
        this.configurations = configurations;
    }
}
