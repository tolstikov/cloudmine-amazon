package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.TrafficPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class TrafficPolicyResponse extends AmazonResponse {

    private TrafficPolicy policy;

    public TrafficPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(final TrafficPolicy policy) {
        this.policy = policy;
    }
}
