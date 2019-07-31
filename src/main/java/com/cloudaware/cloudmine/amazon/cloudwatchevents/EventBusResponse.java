package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class EventBusResponse extends AmazonResponse {
    private String name;
    private String arn;
    private String policy;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getArn() {
        return arn;
    }

    public void setArn(final String arn) {
        this.arn = arn;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(final String policy) {
        this.policy = policy;
    }
}
