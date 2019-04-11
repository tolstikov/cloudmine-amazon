package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.MeshRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MeshesResponse extends AmazonResponse {
    private List<MeshRef> items;

    public List<MeshRef> getItems() {
        return items;
    }

    public void setItems(final List<MeshRef> items) {
        this.items = items;
    }
}
