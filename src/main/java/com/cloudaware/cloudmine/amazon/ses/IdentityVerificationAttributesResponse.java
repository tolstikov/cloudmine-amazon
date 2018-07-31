package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentityVerificationAttributesResponse extends AmazonResponse {

    private List<com.cloudaware.cloudmine.amazon.ses.IdentityVerificationAttributes> identityVerificationAttributes;

    public List<com.cloudaware.cloudmine.amazon.ses.IdentityVerificationAttributes> getIdentityVerificationAttributes() {
        return identityVerificationAttributes;
    }

    public void setIdentityVerificationAttributes(final List<com.cloudaware.cloudmine.amazon.ses.IdentityVerificationAttributes> identityVerificationAttributes) {
        this.identityVerificationAttributes = identityVerificationAttributes;
    }
}
