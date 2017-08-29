package com.cloudaware.cloudmine.amazon.kinesisfirehose;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeliveryStreamNamesResponse extends AmazonResponse {

    private List<String> deliveryStreamNames;

    public List<String> getDeliveryStreamNames() {
        return deliveryStreamNames;
    }

    public void setDeliveryStreamNames(final List<String> deliveryStreamNames) {
        this.deliveryStreamNames = deliveryStreamNames;
    }
}
