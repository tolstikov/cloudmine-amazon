package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.DescribeUserResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class UserDetailsResponse extends AmazonResponse {

    private DescribeUserResult details;

    public DescribeUserResult getDetails() {
        return details;
    }

    public void setDetails(final DescribeUserResult details) {
        this.details = details;
    }
}
