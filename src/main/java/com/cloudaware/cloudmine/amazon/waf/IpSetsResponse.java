package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.IPSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IpSetsResponse extends AmazonResponse {
    private List<IPSetSummary> ipSetSummaries;

    public List<IPSetSummary> getIpSetSummaries() {
        return ipSetSummaries;
    }

    public void setIpSetSummaries(final List<IPSetSummary> ipSetSummaries) {
        this.ipSetSummaries = ipSetSummaries;
    }
}
