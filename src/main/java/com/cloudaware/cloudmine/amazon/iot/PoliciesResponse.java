package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.Policy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PoliciesResponse extends AmazonResponse {

    private List<Policy> policies;

    public void setPolicies(final List<Policy> policies) {
        this.policies = policies;
    }

    public List<Policy> getPolicies() {
        return policies;
    }
}
