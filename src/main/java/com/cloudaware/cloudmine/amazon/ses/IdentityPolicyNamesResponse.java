package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentityPolicyNamesResponse extends AmazonResponse {

    private List<String> policyNames;

    public List<String> getPolicyNames() {
        return policyNames;
    }

    public void setPolicyNames(final List<String> policyNames) {
        this.policyNames = policyNames;
    }
}
