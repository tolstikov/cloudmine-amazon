package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.ScheduledUpdateGroupAction;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ScheduledActionsResponse extends AmazonResponse {

    private List<ScheduledUpdateGroupAction> actions;

    public List<ScheduledUpdateGroupAction> getActions() {
        return actions;
    }

    public void setActions(final List<ScheduledUpdateGroupAction> actions) {
        this.actions = actions;
    }
}
