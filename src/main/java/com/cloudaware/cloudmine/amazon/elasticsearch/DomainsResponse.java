package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.services.elasticsearch.model.ElasticsearchDomainStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DomainsResponse extends AmazonResponse {

    private List<ElasticsearchDomainStatus> domains;

    public List<ElasticsearchDomainStatus> getDomains() {
        return domains;
    }

    public void setDomains(final List<ElasticsearchDomainStatus> domains) {
        this.domains = domains;
    }
}
