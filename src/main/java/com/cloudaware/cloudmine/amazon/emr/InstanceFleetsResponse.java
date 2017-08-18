package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.InstanceFleet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstanceFleetsResponse extends AmazonResponse {

    private List<InstanceFleet> instanceFleets;

    public List<InstanceFleet> getInstanceFleets() {
        return instanceFleets;
    }

    public void setInstanceFleets(final List<InstanceFleet> instanceFleets) {
        this.instanceFleets = instanceFleets;
    }
}
