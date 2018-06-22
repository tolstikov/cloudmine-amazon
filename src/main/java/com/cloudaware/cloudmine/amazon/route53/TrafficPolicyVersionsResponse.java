package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.TrafficPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrafficPolicyVersionsResponse extends AmazonResponse {

    private List<TrafficPolicy> policies;

    public List<TrafficPolicy> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<TrafficPolicy> policies) {
        this.policies = policies;
    }
}
