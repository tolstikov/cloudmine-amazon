package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.services.codestar.model.UserProfileSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UserProfilesResponse extends AmazonResponse {

    private List<UserProfileSummary> userProfiles;

    public List<UserProfileSummary> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(final List<UserProfileSummary> userProfiles) {
        this.userProfiles = userProfiles;
    }
}
