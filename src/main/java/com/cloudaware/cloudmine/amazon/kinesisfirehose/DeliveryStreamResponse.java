package com.cloudaware.cloudmine.amazon.kinesisfirehose;

import com.amazonaws.services.kinesisfirehose.model.DeliveryStreamDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DeliveryStreamResponse extends AmazonResponse {

    private DeliveryStreamDescription deliveryStream;

    public DeliveryStreamDescription getDeliveryStream() {
        return deliveryStream;
    }

    public void setDeliveryStream(final DeliveryStreamDescription deliveryStream) {
        this.deliveryStream = deliveryStream;
    }
}
