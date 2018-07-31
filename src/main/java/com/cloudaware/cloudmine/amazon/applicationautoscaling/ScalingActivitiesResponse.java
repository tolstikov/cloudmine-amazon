package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.services.applicationautoscaling.model.ScalingActivity;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingActivitiesResponse extends AmazonResponse {
    private List<ScalingActivity> scalingActivities;

    public List<ScalingActivity> getScalingActivities() {
        return scalingActivities;
    }

    public void setScalingActivities(final List<ScalingActivity> scalingActivities) {
        this.scalingActivities = scalingActivities;
    }
}
