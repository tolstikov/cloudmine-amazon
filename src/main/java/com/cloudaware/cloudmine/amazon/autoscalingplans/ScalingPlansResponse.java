package com.cloudaware.cloudmine.amazon.autoscalingplans;

import com.amazonaws.services.autoscalingplans.model.ScalingPlan;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingPlansResponse extends AmazonResponse {
    private List<ScalingPlan> scalingPlans;

    public List<ScalingPlan> getScalingPlans() {
        return scalingPlans;
    }

    public void setScalingPlans(final List<ScalingPlan> scalingPlans) {
        this.scalingPlans = scalingPlans;
    }
}
