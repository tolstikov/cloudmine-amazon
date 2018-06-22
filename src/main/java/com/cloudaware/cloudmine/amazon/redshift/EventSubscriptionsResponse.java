package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.EventSubscription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EventSubscriptionsResponse extends AmazonResponse {

    private List<EventSubscription> subscriptions;

    public List<EventSubscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(final List<EventSubscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
