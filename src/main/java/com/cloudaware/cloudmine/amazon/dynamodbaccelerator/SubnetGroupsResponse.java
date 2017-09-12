package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.SubnetGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SubnetGroupsResponse extends AmazonResponse {
    private List<SubnetGroup> subnetGroups;

    public List<SubnetGroup> getSubnetGroups() {
        return subnetGroups;
    }

    public void setSubnetGroups(final List<SubnetGroup> subnetGroups) {
        this.subnetGroups = subnetGroups;
    }
}
