package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualServiceRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VirtualServicesResponse extends AmazonResponse {
    private List<VirtualServiceRef> items;

    public List<VirtualServiceRef> getItems() {
        return items;
    }

    public void setItems(final List<VirtualServiceRef> items) {
        this.items = items;
    }
}
