package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.DeploymentTarget;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentTargetsResponse extends AmazonResponse {
    private List<DeploymentTarget> deploymentTargets;

    public List<DeploymentTarget> getDeploymentTargets() {
        return deploymentTargets;
    }

    public void setDeploymentTargets(final List<DeploymentTarget> deploymentTargets) {
        this.deploymentTargets = deploymentTargets;
    }
}
