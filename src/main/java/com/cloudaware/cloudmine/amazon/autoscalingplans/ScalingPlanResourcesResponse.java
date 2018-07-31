package com.cloudaware.cloudmine.amazon.autoscalingplans;

import com.amazonaws.services.autoscalingplans.model.ScalingPlanResource;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingPlanResourcesResponse extends AmazonResponse {
    private List<ScalingPlanResource> scalingPlans;

    public List<ScalingPlanResource> getScalingPlans() {
        return scalingPlans;
    }

    public void setScalingPlans(final List<ScalingPlanResource> scalingPlans) {
        this.scalingPlans = scalingPlans;
    }
}
