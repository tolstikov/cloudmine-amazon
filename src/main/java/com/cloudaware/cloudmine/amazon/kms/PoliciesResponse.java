package com.cloudaware.cloudmine.amazon.kms;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:27
 */
public final class PoliciesResponse extends AmazonResponse {
    private List<String> policyNames;

    public PoliciesResponse() {
    }

    public PoliciesResponse(final AmazonException exception) {
        super(exception);
    }

    public PoliciesResponse(final List<String> policyNames, final String nextPage) {
        super(nextPage);
        this.policyNames = policyNames;
    }

    public List<String> getPolicyNames() {
        return policyNames;
    }

    public void setPolicyNames(final List<String> policyNames) {
        this.policyNames = policyNames;
    }
}
