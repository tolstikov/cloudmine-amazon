package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.RouteData;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RouteResponse extends AmazonResponse {
    private RouteData route;

    public RouteData getRoute() {
        return route;
    }

    public void setRoute(final RouteData route) {
        this.route = route;
    }
}
