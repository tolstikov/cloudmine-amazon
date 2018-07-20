package com.cloudaware.cloudmine.amazon.cloudformation;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StackPolicyResponse extends AmazonResponse {

    private String policyBody;

    public String getPolicyBody() {
        return policyBody;
    }

    public void setPolicyBody(final String policyBody) {
        this.policyBody = policyBody;
    }
}
