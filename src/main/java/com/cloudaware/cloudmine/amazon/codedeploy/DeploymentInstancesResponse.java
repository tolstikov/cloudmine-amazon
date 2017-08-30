package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.InstanceSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentInstancesResponse extends AmazonResponse {

    private List<InstanceSummary> instances;
    private String errorMessage;

    public List<InstanceSummary> getInstances() {
        return instances;
    }

    public void setInstances(final List<InstanceSummary> instances) {
        this.instances = instances;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
