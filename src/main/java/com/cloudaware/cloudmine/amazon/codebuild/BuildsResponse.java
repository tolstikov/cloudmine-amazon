package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.Build;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class BuildsResponse extends AmazonResponse {

    private List<Build> builds;
    private List<String> buildsNotFound;

    public List<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(final List<Build> builds) {
        this.builds = builds;
    }

    public List<String> getBuildsNotFound() {
        return buildsNotFound;
    }

    public void setBuildsNotFound(final List<String> buildsNotFound) {
        this.buildsNotFound = buildsNotFound;
    }
}
