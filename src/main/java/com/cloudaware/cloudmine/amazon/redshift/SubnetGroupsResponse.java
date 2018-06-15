package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.ClusterSubnetGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SubnetGroupsResponse extends AmazonResponse {

    private List<ClusterSubnetGroup> groups;

    public List<ClusterSubnetGroup> getGroups() {
        return groups;
    }

    public void setGroups(final List<ClusterSubnetGroup> groups) {
        this.groups = groups;
    }
}
