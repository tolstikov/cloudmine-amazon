package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClustersResponse extends AmazonResponse {
    private List<Cluster> clusters;

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(final List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
