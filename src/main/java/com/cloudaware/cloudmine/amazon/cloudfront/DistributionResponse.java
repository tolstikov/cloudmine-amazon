package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.Distribution;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:00
 */
public final class DistributionResponse extends AmazonResponse {
    private Distribution distribution;

    public DistributionResponse() {
    }

    public DistributionResponse(final AmazonException exception) {
        super(exception);
    }

    public DistributionResponse(final Distribution distribution) {
        this.distribution = distribution;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(final Distribution distribution) {
        this.distribution = distribution;
    }
}
