package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.services.route53domains.model.DomainSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DomainsResponse extends AmazonResponse {

    private List<DomainSummary> domains;

    public List<DomainSummary> getDomains() {
        return domains;
    }

    public void setDomains(final List<DomainSummary> domains) {
        this.domains = domains;
    }
}
