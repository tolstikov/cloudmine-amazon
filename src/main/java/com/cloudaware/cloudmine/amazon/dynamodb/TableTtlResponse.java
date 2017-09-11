package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.TimeToLiveDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class TableTtlResponse extends AmazonResponse {
    private TimeToLiveDescription ttl;

    public TimeToLiveDescription getTtl() {
        return ttl;
    }

    public void setTtl(final TimeToLiveDescription ttl) {
        this.ttl = ttl;
    }
}
