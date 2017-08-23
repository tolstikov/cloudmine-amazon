package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.DeliveryChannel;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeliveryChannelsResponse extends AmazonResponse {

    private List<DeliveryChannel> deliveryChannels;

    public List<DeliveryChannel> getDeliveryChannels() {
        return deliveryChannels;
    }

    public void setDeliveryChannels(final List<DeliveryChannel> deliveryChannels) {
        this.deliveryChannels = deliveryChannels;
    }
}
