package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.PlatformSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PlatformSummariesResponse extends AmazonResponse {
    private List<PlatformSummary> platformSummaries;

    public List<PlatformSummary> getPlatformSummaries() {
        return platformSummaries;
    }

    public void setPlatformSummaries(final List<PlatformSummary> platformSummaries) {
        this.platformSummaries = platformSummaries;
    }
}
