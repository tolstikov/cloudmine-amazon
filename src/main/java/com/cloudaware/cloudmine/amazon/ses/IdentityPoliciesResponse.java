package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

public final class IdentityPoliciesResponse extends AmazonResponse {

    private Map<String, String> policies;

    public Map<String, String> getPolicies() {
        return policies;
    }

    public void setPolicies(final Map<String, String> policies) {
        this.policies = policies;
    }
}
