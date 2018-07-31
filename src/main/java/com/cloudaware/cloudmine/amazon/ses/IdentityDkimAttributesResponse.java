package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentityDkimAttributesResponse extends AmazonResponse {

    private List<com.cloudaware.cloudmine.amazon.ses.IdentityDkimAttributes> identityDkimAttributes;

    public List<com.cloudaware.cloudmine.amazon.ses.IdentityDkimAttributes> getIdentityDkimAttributes() {
        return identityDkimAttributes;
    }

    public void setIdentityDkimAttributes(final List<com.cloudaware.cloudmine.amazon.ses.IdentityDkimAttributes> identityDkimAttributes) {
        this.identityDkimAttributes = identityDkimAttributes;
    }
}
