package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentityMailFromDomainAttributesResponse extends AmazonResponse {

    private List<com.cloudaware.cloudmine.amazon.ses.IdentityMailFromDomainAttributes> identityMailFromDomainAttributes;

    public List<com.cloudaware.cloudmine.amazon.ses.IdentityMailFromDomainAttributes> getIdentityMailFromDomainAttributes() {
        return identityMailFromDomainAttributes;
    }

    public void setIdentityMailFromDomainAttributes(final List<com.cloudaware.cloudmine.amazon.ses.IdentityMailFromDomainAttributes> identityMailFromDomainAttributes) {
        this.identityMailFromDomainAttributes = identityMailFromDomainAttributes;
    }
}
