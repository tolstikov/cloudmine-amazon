package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.services.shield.model.Protection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtectionsResponse extends AmazonResponse {
    private List<Protection> protections;

    public List<Protection> getProtections() {
        return protections;
    }

    public void setProtections(final List<Protection> protections) {
        this.protections = protections;
    }
}
