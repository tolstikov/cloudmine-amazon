package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.DeploymentGroupInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentGroupsResponse extends AmazonResponse {

    private List<DeploymentGroupInfo> deploymentGroups;
    private String errorMessage;

    public List<DeploymentGroupInfo> getDeploymentGroups() {
        return deploymentGroups;
    }

    public void setDeploymentGroups(final List<DeploymentGroupInfo> deploymentGroups) {
        this.deploymentGroups = deploymentGroups;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
