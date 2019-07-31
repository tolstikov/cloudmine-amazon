package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.BrokerSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class BrokersResponse extends AmazonResponse {

    private List<BrokerSummary> brokers;

    public void setBrokers(final List<BrokerSummary> brokers) {
        this.brokers = brokers;
    }

    public List<BrokerSummary> getBrokers() {
        return brokers;
    }
}
