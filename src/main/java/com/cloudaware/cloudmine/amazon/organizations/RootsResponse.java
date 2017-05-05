package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Root;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RootsResponse extends AmazonResponse {

    private List<Root> roots;

    public RootsResponse() {
    }

    public RootsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public RootsResponse(final List<Root> roots, final String nextPage) {
        super(nextPage);
        this.roots = roots;
    }

    public List<Root> getRoots() {
        return roots;
    }

    public void setRoots(final List<Root> roots) {
        this.roots = roots;
    }
}
