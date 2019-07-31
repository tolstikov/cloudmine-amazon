package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualRouterRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VirtualRoutersResponse extends AmazonResponse {
    private List<VirtualRouterRef> items;

    public List<VirtualRouterRef> getItems() {
        return items;
    }

    public void setItems(final List<VirtualRouterRef> items) {
        this.items = items;
    }
}
