package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.PolicySummary;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PoliciesResponse extends AmazonResponse {

    private List<PolicySummary> policies;

    public PoliciesResponse() {
    }

    public PoliciesResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public PoliciesResponse(final List<PolicySummary> policies, final String nextPage) {
        super(nextPage);
        this.policies = policies;
    }

    public List<PolicySummary> getPolicies() {
        return policies;
    }

    public void setPolicies(final List<PolicySummary> policies) {
        this.policies = policies;
    }
}
