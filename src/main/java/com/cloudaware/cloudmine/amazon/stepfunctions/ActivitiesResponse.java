package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.services.stepfunctions.model.ActivityListItem;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ActivitiesResponse extends AmazonResponse {

    private List<ActivityListItem> activities;

    public List<ActivityListItem> getActivities() {
        return activities;
    }

    public void setActivities(final List<ActivityListItem> activities) {
        this.activities = activities;
    }
}
