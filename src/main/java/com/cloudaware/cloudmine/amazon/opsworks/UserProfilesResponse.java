package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.UserProfile;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UserProfilesResponse extends AmazonResponse {

    private List<UserProfile> userProfiles;

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(final List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
