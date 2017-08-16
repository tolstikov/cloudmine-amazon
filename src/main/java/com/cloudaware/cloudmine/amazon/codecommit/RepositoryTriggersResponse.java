package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.services.codecommit.model.RepositoryTrigger;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RepositoryTriggersResponse extends AmazonResponse {

    private String configurationId;
    private List<RepositoryTrigger> triggers;

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(final String configurationId) {
        this.configurationId = configurationId;
    }

    public List<RepositoryTrigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(final List<RepositoryTrigger> triggers) {
        this.triggers = triggers;
    }
}
