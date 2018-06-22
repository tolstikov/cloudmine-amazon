package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.services.route53domains.model.OperationSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class OperationsResponse extends AmazonResponse {

    private List<OperationSummary> operations;

    public List<OperationSummary> getOperations() {
        return operations;
    }

    public void setOperations(final List<OperationSummary> operations) {
        this.operations = operations;
    }
}
