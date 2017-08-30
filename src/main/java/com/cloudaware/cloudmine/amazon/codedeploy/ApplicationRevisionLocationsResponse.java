package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.RevisionLocation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApplicationRevisionLocationsResponse extends AmazonResponse {

    private List<RevisionLocation> revisionLocations;

    public List<RevisionLocation> getRevisionLocations() {
        return revisionLocations;
    }

    public void setRevisionLocations(final List<RevisionLocation> revisionLocations) {
        this.revisionLocations = revisionLocations;
    }
}
