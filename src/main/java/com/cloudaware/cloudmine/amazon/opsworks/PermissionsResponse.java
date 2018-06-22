package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.Permission;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PermissionsResponse extends AmazonResponse {

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(final List<Permission> permissions) {
        this.permissions = permissions;
    }
}
