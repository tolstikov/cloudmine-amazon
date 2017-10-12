package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.AccessControlList;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AclResponse extends AmazonResponse {

    private AccessControlList acl;

    public AclResponse(final AmazonException exception) {
        super(exception);
    }

    public AclResponse(final AccessControlList acl) {
        this.acl = acl;
    }

    public AccessControlList getAcl() {
        return acl;
    }

    public void setAcl(final AccessControlList acl) {
        this.acl = acl;
    }
}
