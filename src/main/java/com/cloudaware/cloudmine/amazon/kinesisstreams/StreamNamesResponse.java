package com.cloudaware.cloudmine.amazon.kinesisstreams;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StreamNamesResponse extends AmazonResponse {

    private List<String> streamNames;

    public List<String> getStreamNames() {
        return streamNames;
    }

    public void setStreamNames(final List<String> streamNames) {
        this.streamNames = streamNames;
    }
}
