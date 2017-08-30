package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.ByteMatchSetSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ByteMatchSetsResponse extends AmazonResponse {
    private List<ByteMatchSetSummary> byteMatchSetSummaries;

    public List<ByteMatchSetSummary> getByteMatchSetSummaries() {
        return byteMatchSetSummaries;
    }

    public void setByteMatchSetSummaries(final List<ByteMatchSetSummary> byteMatchSetSummaries) {
        this.byteMatchSetSummaries = byteMatchSetSummaries;
    }
}
