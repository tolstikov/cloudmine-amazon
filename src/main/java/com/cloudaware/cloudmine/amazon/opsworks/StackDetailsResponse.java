package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.StackSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StackDetailsResponse extends AmazonResponse {

    private StackSummary stackDetails;

    public StackSummary getStackDetails() {
        return stackDetails;
    }

    public void setStackDetails(final StackSummary stackDetails) {
        this.stackDetails = stackDetails;
    }
}
