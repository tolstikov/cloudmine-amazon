package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualRouterData;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class VirtualRouterResponse extends AmazonResponse {
    private VirtualRouterData virtualRouter;

    public VirtualRouterData getVirtualRouter() {
        return virtualRouter;
    }

    public void setVirtualRouter(final VirtualRouterData virtualRouter) {
        this.virtualRouter = virtualRouter;
    }
}
