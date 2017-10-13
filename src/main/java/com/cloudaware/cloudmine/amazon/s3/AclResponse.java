package com.cloudaware.cloudmine.amazon.s3;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AclResponse extends AmazonResponse {

    private AccessControlListParsableByGoogleParser acl;

    public AclResponse(final AmazonException exception) {
        super(exception);
    }

    public AclResponse(final AccessControlListParsableByGoogleParser acl) {
        this.acl = acl;
    }

    public AccessControlListParsableByGoogleParser getAcl() {
        return acl;
    }

    public void setAcl(final AccessControlListParsableByGoogleParser acl) {
        this.acl = acl;
    }
}
