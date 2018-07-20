package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StackSetsResponse extends AmazonResponse {

    private List<StackSetSummary> stackSets;

    public List<StackSetSummary> getStackSets() {
        return stackSets;
    }

    public void setStackSets(final List<StackSetSummary> stackSets) {
        this.stackSets = stackSets;
    }
}
