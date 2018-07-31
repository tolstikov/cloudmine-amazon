package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ConfigurationSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationSetsResponse extends AmazonResponse {

    private List<ConfigurationSet> configurationSets;

    public List<ConfigurationSet> getConfigurationSets() {
        return configurationSets;
    }

    public void setConfigurationSets(final List<ConfigurationSet> configurationSets) {
        this.configurationSets = configurationSets;
    }
}
