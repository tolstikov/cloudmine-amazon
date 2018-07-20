package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackSetOperation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StackSetOperationResponse extends AmazonResponse {

    private StackSetOperation operation;

    public StackSetOperation getOperation() {
        return operation;
    }

    public void setOperation(final StackSetOperation operation) {
        this.operation = operation;
    }
}
