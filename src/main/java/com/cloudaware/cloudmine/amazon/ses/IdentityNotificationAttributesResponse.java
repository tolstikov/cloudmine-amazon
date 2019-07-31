package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class IdentityNotificationAttributesResponse extends AmazonResponse {

    private List<com.cloudaware.cloudmine.amazon.ses.IdentityNotificationAttributes> identityNotificationAttributes;

    public List<com.cloudaware.cloudmine.amazon.ses.IdentityNotificationAttributes> getIdentityNotificationAttributes() {
        return identityNotificationAttributes;
    }

    public void setIdentityNotificationAttributes(final List<com.cloudaware.cloudmine.amazon.ses.IdentityNotificationAttributes> identityNotificationAttributes) {
        this.identityNotificationAttributes = identityNotificationAttributes;
    }
}
