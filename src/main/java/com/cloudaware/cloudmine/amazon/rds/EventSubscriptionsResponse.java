package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.EventSubscription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EventSubscriptionsResponse extends AmazonResponse {

    private List<EventSubscription> eventSubscriptions;

    public List<EventSubscription> getEventSubscriptions() {
        return eventSubscriptions;
    }

    public void setEventSubscriptions(final List<EventSubscription> eventSubscriptions) {
        this.eventSubscriptions = eventSubscriptions;
    }
}
