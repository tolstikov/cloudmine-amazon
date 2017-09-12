package com.cloudaware.cloudmine.amazon.dynamodbstreams;

import com.amazonaws.services.dynamodbv2.model.StreamDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StreamResponse extends AmazonResponse {
    private StreamDescription streamDescription;

    public StreamDescription getStreamDescription() {
        return streamDescription;
    }

    public void setStreamDescription(final StreamDescription streamDescription) {
        this.streamDescription = streamDescription;
    }
}
