package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualNodeRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VirtualNodesResponse extends AmazonResponse {
    private List<VirtualNodeRef> items;

    public List<VirtualNodeRef> getItems() {
        return items;
    }

    public void setItems(final List<VirtualNodeRef> items) {
        this.items = items;
    }
}
