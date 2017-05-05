package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 14:56
 */
public final class ClustersResponse extends AmazonResponse {
    private List<Cluster> clusters;

    public ClustersResponse() {
    }

    public ClustersResponse(final AmazonException exception) {
        super(exception);
    }

    public ClustersResponse(final List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(final List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
