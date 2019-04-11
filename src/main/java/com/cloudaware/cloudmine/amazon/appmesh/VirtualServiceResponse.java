package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.VirtualServiceData;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class VirtualServiceResponse extends AmazonResponse {
    private VirtualServiceData virtualService;

    public VirtualServiceData getVirtualService() {
        return virtualService;
    }

    public void setVirtualService(final VirtualServiceData virtualService) {
        this.virtualService = virtualService;
    }
}
