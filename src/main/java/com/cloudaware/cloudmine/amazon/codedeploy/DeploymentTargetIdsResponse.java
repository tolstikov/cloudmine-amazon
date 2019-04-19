package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentTargetIdsResponse extends AmazonResponse {
    private List<String> deploymentTargetIds;

    public List<String> getDeploymentTargetIds() {
        return deploymentTargetIds;
    }

    public void setDeploymentTargetIds(final List<String> deploymentTargetIds) {
        this.deploymentTargetIds = deploymentTargetIds;
    }
}
