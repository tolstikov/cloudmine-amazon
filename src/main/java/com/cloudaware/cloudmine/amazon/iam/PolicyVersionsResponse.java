package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.PolicyVersion;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:33
 */
public final class PolicyVersionsResponse extends AmazonResponse {
    private List<PolicyVersion> policyVersions;

    public PolicyVersionsResponse() {
    }

    public PolicyVersionsResponse(final AmazonException exception) {
        super(exception);
    }

    public PolicyVersionsResponse(final List<PolicyVersion> policyVersions, final String nextPage) {
        super(nextPage);
        this.policyVersions = policyVersions;
    }

    public List<PolicyVersion> getPolicyVersions() {
        return policyVersions;
    }

    public void setPolicyVersions(final List<PolicyVersion> policyVersions) {
        this.policyVersions = policyVersions;
    }
}
