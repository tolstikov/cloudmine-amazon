package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.services.codestar.model.Resource;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ResourcesResponse extends AmazonResponse {

    private List<Resource> resources;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(final List<Resource> resources) {
        this.resources = resources;
    }
}
