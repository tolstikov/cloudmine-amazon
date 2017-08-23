package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.DeliveryChannelStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeliveryChannelsStatusResponse extends AmazonResponse {

    private List<DeliveryChannelStatus> deliveryChannelsStatus;

    public List<DeliveryChannelStatus> getDeliveryChannelsStatus() {
        return deliveryChannelsStatus;
    }

    public void setDeliveryChannelsStatus(final List<DeliveryChannelStatus> deliveryChannelsStatus) {
        this.deliveryChannelsStatus = deliveryChannelsStatus;
    }
}
