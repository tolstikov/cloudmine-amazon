package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.ScalingPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PoliciesResponse extends AmazonResponse {

    private List<ScalingPolicy> policies;

    public List<ScalingPolicy> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<ScalingPolicy> policies) {
        this.policies = policies;
    }
}
