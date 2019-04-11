package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.RouteRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RoutesResponse extends AmazonResponse {
    private List<RouteRef> items;

    public List<RouteRef> getItems() {
        return items;
    }

    public void setItems(final List<RouteRef> items) {
        this.items = items;
    }
}
