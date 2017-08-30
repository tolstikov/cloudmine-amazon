package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.DeploymentConfigInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DeploymentConfigResponse extends AmazonResponse {

    private DeploymentConfigInfo deploymentConfig;

    public DeploymentConfigInfo getDeploymentConfig() {
        return deploymentConfig;
    }

    public void setDeploymentConfig(final DeploymentConfigInfo deploymentConfig) {
        this.deploymentConfig = deploymentConfig;
    }
}
