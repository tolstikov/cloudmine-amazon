package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StackSetResponse extends AmazonResponse {

    private StackSet stackSet;

    public StackSet getStackSet() {
        return stackSet;
    }

    public void setStackSet(final StackSet stackSet) {
        this.stackSet = stackSet;
    }
}
