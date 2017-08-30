package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.services.shield.model.Subscription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SubscriptionResponse extends AmazonResponse {
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(final Subscription subscription) {
        this.subscription = subscription;
    }
}
