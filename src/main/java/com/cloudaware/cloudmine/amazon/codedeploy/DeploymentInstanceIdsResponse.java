package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentInstanceIdsResponse extends AmazonResponse {

    private List<String> deploymentInstanceIds;

    public List<String> getDeploymentInstanceIds() {
        return deploymentInstanceIds;
    }

    public void setDeploymentInstanceIds(final List<String> deploymentInstanceIds) {
        this.deploymentInstanceIds = deploymentInstanceIds;
    }
}
