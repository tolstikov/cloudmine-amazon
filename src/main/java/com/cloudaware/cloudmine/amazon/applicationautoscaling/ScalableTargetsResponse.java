package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.services.applicationautoscaling.model.ScalableTarget;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalableTargetsResponse extends AmazonResponse {
    private List<ScalableTarget> scalableTargets;

    public List<ScalableTarget> getScalableTargets() {
        return scalableTargets;
    }

    public void setScalableTargets(final List<ScalableTarget> scalableTargets) {
        this.scalableTargets = scalableTargets;
    }
}
