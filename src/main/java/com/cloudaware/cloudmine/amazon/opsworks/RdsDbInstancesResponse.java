package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.RdsDbInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RdsDbInstancesResponse extends AmazonResponse {

    private List<RdsDbInstance> rdsDbInstances;

    public List<RdsDbInstance> getRdsDbInstances() {
        return rdsDbInstances;
    }

    public void setRdsDbInstances(final List<RdsDbInstance> rdsDbInstances) {
        this.rdsDbInstances = rdsDbInstances;
    }
}
