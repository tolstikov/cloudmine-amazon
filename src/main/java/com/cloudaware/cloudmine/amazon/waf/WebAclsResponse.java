package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.WebACLSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public class WebAclsResponse extends AmazonResponse {
    private List<WebACLSummary> webAclSummaries;

    public final List<WebACLSummary> getWebAclSummaries() {
        return webAclSummaries;
    }

    public final void setWebAclSummaries(final List<WebACLSummary> webAclSummaries) {
        this.webAclSummaries = webAclSummaries;
    }
}
