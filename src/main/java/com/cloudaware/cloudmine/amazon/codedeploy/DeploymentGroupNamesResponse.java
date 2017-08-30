package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentGroupNamesResponse extends AmazonResponse {

    private String applicationName;
    private List<String> deploymentGroupNames;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public List<String> getDeploymentGroupNames() {
        return deploymentGroupNames;
    }

    public void setDeploymentGroupNames(final List<String> deploymentGroupNames) {
        this.deploymentGroupNames = deploymentGroupNames;
    }
}
