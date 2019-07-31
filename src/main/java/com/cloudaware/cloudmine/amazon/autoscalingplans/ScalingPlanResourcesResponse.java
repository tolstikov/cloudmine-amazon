package com.cloudaware.cloudmine.amazon.autoscalingplans;

import com.amazonaws.services.autoscalingplans.model.ScalingPlanResource;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingPlanResourcesResponse extends AmazonResponse {
    private List<ScalingPlanResource> scalingPlanResources;

    public List<ScalingPlanResource> getScalingPlanResources() {
        return scalingPlanResources;
    }

    public void setScalingPlanResources(final List<ScalingPlanResource> scalingPlanResources) {
        this.scalingPlanResources = scalingPlanResources;
    }
}
