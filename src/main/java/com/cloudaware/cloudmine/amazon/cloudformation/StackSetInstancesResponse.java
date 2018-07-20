package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackInstanceSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StackSetInstancesResponse extends AmazonResponse {

    private List<StackInstanceSummary> instances;

    public List<StackInstanceSummary> getInstances() {
        return instances;
    }

    public void setInstances(final List<StackInstanceSummary> instances) {
        this.instances = instances;
    }
}
