package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.Activity;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScalingActivitiesResponse extends AmazonResponse {

    private List<Activity> activities;

    public void setActivities(final List<Activity> activities) {
        this.activities = activities;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
