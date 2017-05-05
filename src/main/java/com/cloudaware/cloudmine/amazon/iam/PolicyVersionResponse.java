package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.PolicyVersion;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:37
 */
public final class PolicyVersionResponse extends AmazonResponse {
    private PolicyVersion policyVersion;

    public PolicyVersionResponse() {
    }

    public PolicyVersionResponse(final AmazonException exception) {
        super(exception);
    }

    public PolicyVersionResponse(final PolicyVersion policyVersion) {
        this.policyVersion = policyVersion;
    }

    public PolicyVersion getPolicyVersion() {
        return policyVersion;
    }

    public void setPolicyVersion(final PolicyVersion policyVersion) {
        this.policyVersion = policyVersion;
    }
}
