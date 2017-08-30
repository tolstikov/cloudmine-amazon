package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.RevisionLocation;

import java.util.List;

public final class ApplicationRevisionsRequest {

    private List<RevisionLocation> revisionLocations;

    public List<RevisionLocation> getRevisionLocations() {
        return revisionLocations;
    }

    public void setRevisionLocations(final List<RevisionLocation> revisionLocations) {
        this.revisionLocations = revisionLocations;
    }
}
