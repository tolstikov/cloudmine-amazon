package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualNodeData;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class VirtualNodeResponse extends AmazonResponse {
    private VirtualNodeData virtualNode;

    public VirtualNodeData getVirtualNode() {
        return virtualNode;
    }

    public void setVirtualNode(final VirtualNodeData virtualNode) {
        this.virtualNode = virtualNode;
    }
}
