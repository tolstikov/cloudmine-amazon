package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.services.elasticsearch.model.ReservedElasticsearchInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ReservedInstancesResponse extends AmazonResponse {

    private List<ReservedElasticsearchInstance> reservedInstances;

    public List<ReservedElasticsearchInstance> getReservedInstances() {
        return reservedInstances;
    }

    public void setReservedInstances(final List<ReservedElasticsearchInstance> reservedInstances) {
        this.reservedInstances = reservedInstances;
    }
}
