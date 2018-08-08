package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.SharedImagePermissions;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SharedImagePermissionsResponse extends AmazonResponse {

    private List<SharedImagePermissions> sharedImagePermissions;

    public List<SharedImagePermissions> getSharedImagePermissions() {
        return sharedImagePermissions;
    }

    public void setSharedImagePermissions(final List<SharedImagePermissions> sharedImagePermissions) {
        this.sharedImagePermissions = sharedImagePermissions;
    }
}
