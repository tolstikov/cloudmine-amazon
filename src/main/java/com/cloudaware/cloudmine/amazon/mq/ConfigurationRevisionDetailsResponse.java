package com.cloudaware.cloudmine.amazon.mq;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ConfigurationRevisionDetailsResponse extends AmazonResponse {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }
}
