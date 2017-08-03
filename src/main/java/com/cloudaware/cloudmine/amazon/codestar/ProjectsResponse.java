package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.services.codestar.model.ProjectSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProjectsResponse extends AmazonResponse {

    private List<ProjectSummary> projects;

    public List<ProjectSummary> getProjects() {
        return projects;
    }

    public void setProjects(final List<ProjectSummary> projects) {
        this.projects = projects;
    }
}
