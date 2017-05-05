package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.ClusterSummary;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:30
 */
public final class ClustersResponse extends AmazonResponse {
    private List<ClusterSummary> clusters;

    public ClustersResponse() {
    }

    public ClustersResponse(final AmazonException exception) {
        super(exception);
    }

    public ClustersResponse(final List<ClusterSummary> clusters, final String nextPage) {
        super(nextPage);
        this.clusters = clusters;
    }

    public List<ClusterSummary> getClusters() {
        return clusters;
    }

    public void setClusters(final List<ClusterSummary> clusters) {
        this.clusters = clusters;
    }
}
