package com.cloudaware.cloudmine.amazon.dynamodbstreams;

import com.amazonaws.services.dynamodbv2.model.Stream;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StreamsResponse extends AmazonResponse {
    private List<Stream> streams;

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(final List<Stream> streams) {
        this.streams = streams;
    }
}
