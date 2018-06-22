package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.ElasticIp;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ElasticIpsResponse extends AmazonResponse {

    private List<ElasticIp> elasticIps;

    public List<ElasticIp> getElasticIps() {
        return elasticIps;
    }

    public void setElasticIps(final List<ElasticIp> elasticIps) {
        this.elasticIps = elasticIps;
    }
}
