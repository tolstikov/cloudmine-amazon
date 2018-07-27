package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.PublicKeySummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PublicKeysResponse extends AmazonResponse {
    private List<PublicKeySummary> publicKeySummaries;

    public List<PublicKeySummary> getPublicKeySummaries() {
        return publicKeySummaries;
    }

    public void setPublicKeySummaries(final List<PublicKeySummary> publicKeySummaries) {
        this.publicKeySummaries = publicKeySummaries;
    }
}
