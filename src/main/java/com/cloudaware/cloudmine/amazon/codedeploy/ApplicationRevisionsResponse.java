package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.RevisionInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApplicationRevisionsResponse extends AmazonResponse {

    private String applicationName;
    private String errorMessage;
    private List<RevisionInfo> revisions;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<RevisionInfo> getRevisions() {
        return revisions;
    }

    public void setRevisions(final List<RevisionInfo> revisions) {
        this.revisions = revisions;
    }
}
