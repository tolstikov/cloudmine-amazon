package com.cloudaware.cloudmine.amazon.appstream;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AssociatedFleetsResponse extends AmazonResponse {
    private List<String> fleetNames;

    public List<String> getFleetNames() {
        return fleetNames;
    }

    public void setFleetNames(final List<String> fleetNames) {
        this.fleetNames = fleetNames;
    }
}
