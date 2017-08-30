package com.cloudaware.cloudmine.amazon.codedeploy;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentConfigsResponse extends AmazonResponse {

    private List<String> deploymentConfigs;

    public List<String> getDeploymentConfigs() {
        return deploymentConfigs;
    }

    public void setDeploymentConfigs(final List<String> deploymentConfigs) {
        this.deploymentConfigs = deploymentConfigs;
    }
}
