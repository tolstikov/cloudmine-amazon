package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StackSetInstanceResponse extends AmazonResponse {

    private StackInstance instance;

    public StackInstance getInstance() {
        return instance;
    }

    public void setInstance(final StackInstance instance) {
        this.instance = instance;
    }
}
