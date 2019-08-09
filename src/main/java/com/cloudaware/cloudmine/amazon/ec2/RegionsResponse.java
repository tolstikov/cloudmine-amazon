package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Region;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RegionsResponse extends AmazonResponse {
    private List<Region> regions;

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(final List<Region> regions) {
        this.regions = regions;
    }
}
