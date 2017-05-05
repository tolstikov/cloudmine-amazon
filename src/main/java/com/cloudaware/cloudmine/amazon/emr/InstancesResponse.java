package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Instance;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:47
 */
public final class InstancesResponse extends AmazonResponse {
    private List<Instance> instances;

    public InstancesResponse() {
    }

    public InstancesResponse(final AmazonException exception) {
        super(exception);
    }

    public InstancesResponse(final List<Instance> instances, final String nextPage) {
        super(nextPage);
        this.instances = instances;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(final List<Instance> instances) {
        this.instances = instances;
    }
}
