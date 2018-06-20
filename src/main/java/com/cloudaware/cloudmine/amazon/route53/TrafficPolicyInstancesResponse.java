package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.TrafficPolicyInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrafficPolicyInstancesResponse extends AmazonResponse {

    private List<TrafficPolicyInstance> instances;

    public List<TrafficPolicyInstance> getInstances() {
        return instances;
    }

    public void setInstances(final List<TrafficPolicyInstance> instances) {
        this.instances = instances;
    }
}
