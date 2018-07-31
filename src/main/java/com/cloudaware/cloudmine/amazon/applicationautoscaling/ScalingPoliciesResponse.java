package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.services.applicationautoscaling.model.ScalingPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingPoliciesResponse extends AmazonResponse {
    private List<ScalingPolicy> scalingPolicies;

    public List<ScalingPolicy> getScalingPolicies() {
        return scalingPolicies;
    }

    public void setScalingPolicies(final List<ScalingPolicy> scalingPolicies) {
        this.scalingPolicies = scalingPolicies;
    }
}
