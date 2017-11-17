package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.DevEndpoint;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DevEndpointsResponse extends AmazonResponse {

    private List<DevEndpoint> devEndpoints;

    public List<DevEndpoint> getDevEndpoints() {
        return devEndpoints;
    }

    public void setDevEndpoints(final List<DevEndpoint> devEndpoints) {
        this.devEndpoints = devEndpoints;
    }
}
