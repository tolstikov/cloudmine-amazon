package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackSetOperationSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StackSetOperationsResponse extends AmazonResponse {

    private List<StackSetOperationSummary> operations;

    public List<StackSetOperationSummary> getOperations() {
        return operations;
    }

    public void setOperations(final List<StackSetOperationSummary> operations) {
        this.operations = operations;
    }
}
