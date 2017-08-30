package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.DeploymentInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentsResponse extends AmazonResponse {

    private List<DeploymentInfo> deployments;

    public List<DeploymentInfo> getDeployments() {
        return deployments;
    }

    public void setDeployments(final List<DeploymentInfo> deployments) {
        this.deployments = deployments;
    }
}
