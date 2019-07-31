package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.Fleet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class FleetsResponse extends AmazonResponse {

    private List<Fleet> fleets;

    public List<Fleet> getFleets() {
        return fleets;
    }

    public void setFleets(final List<Fleet> fleets) {
        this.fleets = fleets;
    }
}
