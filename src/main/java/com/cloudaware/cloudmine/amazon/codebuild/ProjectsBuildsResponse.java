package com.cloudaware.cloudmine.amazon.codebuild;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProjectsBuildsResponse extends AmazonResponse {

    private List<String> buildIds;

    public List<String> getBuildIds() {
        return buildIds;
    }

    public void setBuildIds(final List<String> buildIds) {
        this.buildIds = buildIds;
    }
}
