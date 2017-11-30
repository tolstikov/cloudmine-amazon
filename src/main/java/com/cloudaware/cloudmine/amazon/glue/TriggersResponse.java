package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Trigger;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TriggersResponse extends AmazonResponse {

    private List<Trigger> triggers;

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(final List<Trigger> triggers) {
        this.triggers = triggers;
    }
}
