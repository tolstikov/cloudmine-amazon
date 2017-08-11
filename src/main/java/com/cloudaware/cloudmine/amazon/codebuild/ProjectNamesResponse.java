package com.cloudaware.cloudmine.amazon.codebuild;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProjectNamesResponse extends AmazonResponse {

    private List<String> projectNames;

    public List<String> getProjectNames() {
        return projectNames;
    }

    public void setProjectNames(final List<String> projectNames) {
        this.projectNames = projectNames;
    }
}
