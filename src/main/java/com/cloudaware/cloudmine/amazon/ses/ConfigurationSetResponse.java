package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ConfigurationSet;
import com.amazonaws.services.simpleemail.model.EventDestination;
import com.amazonaws.services.simpleemail.model.ReputationOptions;
import com.amazonaws.services.simpleemail.model.TrackingOptions;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ConfigurationSetResponse extends AmazonResponse {

    private ConfigurationSet configurationSet;
    private List<EventDestination> eventDestinations;
    private ReputationOptions reputationOptions;
    private TrackingOptions trackingOptions;

    public ConfigurationSet getConfigurationSet() {
        return configurationSet;
    }

    public void setConfigurationSet(final ConfigurationSet configurationSet) {
        this.configurationSet = configurationSet;
    }

    public List<EventDestination> getEventDestinations() {
        return eventDestinations;
    }

    public void setEventDestinations(final List<EventDestination> eventDestinations) {
        this.eventDestinations = eventDestinations;
    }

    public ReputationOptions getReputationOptions() {
        return reputationOptions;
    }

    public void setReputationOptions(final ReputationOptions reputationOptions) {
        this.reputationOptions = reputationOptions;
    }

    public TrackingOptions getTrackingOptions() {
        return trackingOptions;
    }

    public void setTrackingOptions(final TrackingOptions trackingOptions) {
        this.trackingOptions = trackingOptions;
    }
}
