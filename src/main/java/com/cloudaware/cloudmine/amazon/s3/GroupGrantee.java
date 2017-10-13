package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.Grantee;

public final class GroupGrantee implements Grantee {

    private String groupUri;
    private String groupName;

    @Override
    public String getTypeIdentifier() {
        return "uri";
    }

    @Override
    public void setIdentifier(final String uri) {
        this.groupUri = uri;
    }

    @Override
    public String getIdentifier() {
        return this.groupUri;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }
}
