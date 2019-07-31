package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SendQuotaResponse extends AmazonResponse {

    private Double max24HourSend;
    private Double maxSendRate;
    private Double sentLast24Hours;

    public Double getMax24HourSend() {
        return max24HourSend;
    }

    public void setMax24HourSend(final Double max24HourSend) {
        this.max24HourSend = max24HourSend;
    }

    public Double getMaxSendRate() {
        return maxSendRate;
    }

    public void setMaxSendRate(final Double maxSendRate) {
        this.maxSendRate = maxSendRate;
    }

    public Double getSentLast24Hours() {
        return sentLast24Hours;
    }

    public void setSentLast24Hours(final Double sentLast24Hours) {
        this.sentLast24Hours = sentLast24Hours;
    }
}
