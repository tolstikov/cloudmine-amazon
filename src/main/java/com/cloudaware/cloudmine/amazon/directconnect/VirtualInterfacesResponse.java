package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.services.directconnect.model.VirtualInterface;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:20
 */
public final class VirtualInterfacesResponse extends AmazonResponse {
    private List<VirtualInterface> virtualInterfaces;

    public VirtualInterfacesResponse() {
    }

    public VirtualInterfacesResponse(final AmazonException exception) {
        super(exception);
    }

    public VirtualInterfacesResponse(final List<VirtualInterface> virtualInterfaces) {
        this.virtualInterfaces = virtualInterfaces;
    }

    public List<VirtualInterface> getVirtualInterfaces() {
        return virtualInterfaces;
    }

    public void setVirtualInterfaces(final List<VirtualInterface> virtualInterfaces) {
        this.virtualInterfaces = virtualInterfaces;
    }
}
