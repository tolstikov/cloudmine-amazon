package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.PlacementGroup;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:41
 */
public final class PlacementGroupsResponse extends AmazonResponse {
    private List<PlacementGroup> placementGroups;

    public PlacementGroupsResponse() {
    }

    public PlacementGroupsResponse(final AmazonException exception) {
        super(exception);
    }

    public PlacementGroupsResponse(final List<PlacementGroup> placementGroups) {
        this.placementGroups = placementGroups;
    }

    public List<PlacementGroup> getPlacementGroups() {
        return placementGroups;
    }

    public void setPlacementGroups(final List<PlacementGroup> placementGroups) {
        this.placementGroups = placementGroups;
    }
}
