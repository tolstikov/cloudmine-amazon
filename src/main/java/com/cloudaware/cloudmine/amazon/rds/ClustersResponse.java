package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.redshift.model.Cluster;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 20:18
 */
public final class ClustersResponse extends AmazonResponse {
    private List<Cluster> clusters;

    public ClustersResponse() {
    }

    public ClustersResponse(final AmazonException exception) {
        super(exception);
    }

    public ClustersResponse(final List<Cluster> clusters, final String nextPage) {
        super(nextPage);
        this.clusters = clusters;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(final List<Cluster> clusters) {
        this.clusters = clusters;
    }
}
