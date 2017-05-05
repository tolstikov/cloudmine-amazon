package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:33
 */
public final class ClusterResponse extends AmazonResponse {
    private Cluster cluster;

    public ClusterResponse() {
    }

    public ClusterResponse(final AmazonException exception) {
        super(exception);
    }

    public ClusterResponse(final Cluster cluster) {
        this.cluster = cluster;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(final Cluster cluster) {
        this.cluster = cluster;
    }
}
