package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.services.applicationautoscaling.model.ScheduledAction;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScheduledActionsResponse extends AmazonResponse {
    private List<ScheduledAction> scheduledActions;

    public List<ScheduledAction> getScheduledActions() {
        return scheduledActions;
    }

    public void setScheduledActions(final List<ScheduledAction> scheduledActions) {
        this.scheduledActions = scheduledActions;
    }
}
