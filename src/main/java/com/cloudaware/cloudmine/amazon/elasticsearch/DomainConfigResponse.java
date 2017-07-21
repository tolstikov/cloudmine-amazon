package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.services.elasticsearch.model.ElasticsearchDomainConfig;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DomainConfigResponse extends AmazonResponse {

    private ElasticsearchDomainConfig domainConfig;

    public void setDomainConfig(final ElasticsearchDomainConfig domainConfig) {
        this.domainConfig = domainConfig;
    }

    public ElasticsearchDomainConfig getDomainConfig() {
        return domainConfig;
    }
}
