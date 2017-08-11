package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.Project;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProjectsResponse extends AmazonResponse {

    private List<Project> projects;
    private List<String> projectsNotFound;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(final List<Project> projects) {
        this.projects = projects;
    }

    public List<String> getProjectsNotFound() {
        return projectsNotFound;
    }

    public void setProjectsNotFound(final List<String> projectsNotFound) {
        this.projectsNotFound = projectsNotFound;
    }
}
