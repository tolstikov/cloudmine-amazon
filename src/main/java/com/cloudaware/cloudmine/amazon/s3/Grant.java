package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.Grantee;

public final class Grant {
    private Grantee grantee;
    private String permission;

    public Grant(final com.amazonaws.services.s3.model.Grant in) {
        this.grantee = in.getGrantee();
        this.permission = in.getPermission() == null ? null : in.getPermission().toString();
    }

    public Grantee getGrantee() {
        return grantee;
    }

    public void setGrantee(final Grantee grantee) {
        this.grantee = grantee;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(final String permission) {
        this.permission = permission;
    }
}
