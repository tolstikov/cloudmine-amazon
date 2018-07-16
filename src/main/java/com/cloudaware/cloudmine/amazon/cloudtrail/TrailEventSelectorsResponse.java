package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.EventSelector;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrailEventSelectorsResponse extends AmazonResponse {

    private List<EventSelector> eventsSelectors;
    private String trailArn;

    public List<EventSelector> getEventsSelectors() {
        return eventsSelectors;
    }

    public void setEventsSelectors(final List<EventSelector> eventsSelectors) {
        this.eventsSelectors = eventsSelectors;
    }

    public String getTrailArn() {
        return trailArn;
    }

    public void setTrailArn(final String trailArn) {
        this.trailArn = trailArn;
    }
}
