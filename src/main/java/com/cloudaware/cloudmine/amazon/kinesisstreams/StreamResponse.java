package com.cloudaware.cloudmine.amazon.kinesisstreams;

import com.amazonaws.services.kinesis.model.StreamDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StreamResponse extends AmazonResponse {

    private StreamDescription stream;

    public StreamDescription getStream() {
        return stream;
    }

    public void setStream(final StreamDescription stream) {
        this.stream = stream;
    }
}
