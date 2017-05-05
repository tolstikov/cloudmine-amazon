package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.CacheSubnetGroup;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:35
 */
public final class SubnetGroupsResponse extends AmazonResponse {
    private List<CacheSubnetGroup> subnetGroups;

    public SubnetGroupsResponse() {
    }

    public SubnetGroupsResponse(final AmazonException exception) {
        super(exception);
    }

    public SubnetGroupsResponse(final List<CacheSubnetGroup> subnetGroups, final String nextPage) {
        super(nextPage);
        this.subnetGroups = subnetGroups;
    }

    public List<CacheSubnetGroup> getSubnetGroups() {
        return subnetGroups;
    }

    public void setSubnetGroups(final List<CacheSubnetGroup> subnetGroups) {
        this.subnetGroups = subnetGroups;
    }
}
