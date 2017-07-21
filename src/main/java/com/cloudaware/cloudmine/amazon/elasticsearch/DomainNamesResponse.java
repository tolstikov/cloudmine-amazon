package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.services.elasticsearch.model.DomainInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DomainNamesResponse extends AmazonResponse {

    private List<DomainInfo> domainNames;

    public List<DomainInfo> getDomainNames() {
        return domainNames;
    }

    public void setDomainNames(final List<DomainInfo> domainNames) {
        this.domainNames = domainNames;
    }
}
