package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.services.codecommit.model.RepositoryNameIdPair;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RepositoriesResponse  extends AmazonResponse {

    private List<RepositoryNameIdPair> repositories;

    public List<RepositoryNameIdPair> getRepositories() {
        return repositories;
    }

    public void setRepositories(final List<RepositoryNameIdPair> repositories) {
        this.repositories = repositories;
    }
}
