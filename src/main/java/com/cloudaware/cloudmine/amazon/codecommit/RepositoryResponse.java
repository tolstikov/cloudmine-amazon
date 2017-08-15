package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.services.codecommit.model.RepositoryMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RepositoryResponse extends AmazonResponse {

    private RepositoryMetadata repository;

    public RepositoryMetadata getRepository() {
        return repository;
    }

    public void setRepository(final RepositoryMetadata repository) {
        this.repository = repository;
    }
}
