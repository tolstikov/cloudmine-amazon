package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.Limit;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AccountLimitsResponse extends AmazonResponse {

    private List<Limit> limits;

    public List<Limit> getLimits() {
        return limits;
    }

    public void setLimits(final List<Limit> limits) {
        this.limits = limits;
    }
}
