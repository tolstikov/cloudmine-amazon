package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.MeshData;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class MeshResponse extends AmazonResponse {
    private MeshData mesh;

    public MeshData getMesh() {
        return mesh;
    }

    public void setMesh(final MeshData mesh) {
        this.mesh = mesh;
    }
}
