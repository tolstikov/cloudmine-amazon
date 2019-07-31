package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.amazonaws.services.cloudwatchevents.model.Target;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TargetsResponse extends AmazonResponse {
    private List<Target> targets;

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(final List<Target> targets) {
        this.targets = targets;
    }
}
