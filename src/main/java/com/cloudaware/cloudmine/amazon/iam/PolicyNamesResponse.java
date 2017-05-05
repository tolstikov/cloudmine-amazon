package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:26
 */
public final class PolicyNamesResponse extends AmazonResponse {
    private List<String> policyNames;

    public PolicyNamesResponse() {
    }

    public PolicyNamesResponse(final AmazonException exception) {
        super(exception);
    }

    public PolicyNamesResponse(final List<String> policyNames, final String nextPage) {
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
