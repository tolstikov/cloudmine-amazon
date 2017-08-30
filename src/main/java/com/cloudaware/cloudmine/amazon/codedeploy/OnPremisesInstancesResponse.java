package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.InstanceInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class OnPremisesInstancesResponse extends AmazonResponse {

    private List<InstanceInfo> instances;

    public List<InstanceInfo> getInstances() {
        return instances;
    }

    public void setInstances(final List<InstanceInfo> instances) {
        this.instances = instances;
    }
}
