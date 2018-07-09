package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.DescribeBrokerResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class BrokerDetailsResponse extends AmazonResponse {

    private DescribeBrokerResult details;

    public void setDetails(final DescribeBrokerResult details) {
        this.details = details;
    }

    public DescribeBrokerResult getDetails() {
        return details;
    }
}
