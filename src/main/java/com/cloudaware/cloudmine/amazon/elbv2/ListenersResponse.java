package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.Listener;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ListenersResponse extends AmazonResponse {

    private List<Listener> listeners;

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(final List<Listener> listeners) {
        this.listeners = listeners;
    }
}
