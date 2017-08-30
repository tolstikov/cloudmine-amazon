package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentIdsResponse extends AmazonResponse {

    private List<String> deploymentIds;

    public List<String> getDeploymentIds() {
        return deploymentIds;
    }

    public void setDeploymentIds(final List<String> deploymentIds) {
        this.deploymentIds = deploymentIds;
    }
}
