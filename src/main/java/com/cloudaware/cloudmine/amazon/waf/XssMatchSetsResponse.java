package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.XssMatchSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class XssMatchSetsResponse extends AmazonResponse {
    private List<XssMatchSetSummary> xssMatchSetSummaries;

    public List<XssMatchSetSummary> getXssMatchSetSummaries() {
        return xssMatchSetSummaries;
    }

    public void setXssMatchSetSummaries(final List<XssMatchSetSummary> xssMatchSetSummaries) {
        this.xssMatchSetSummaries = xssMatchSetSummaries;
    }
}
