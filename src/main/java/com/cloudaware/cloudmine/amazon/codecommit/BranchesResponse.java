package com.cloudaware.cloudmine.amazon.codecommit;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class BranchesResponse extends AmazonResponse {

    private List<String> branches;

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(final List<String> branches) {
        this.branches = branches;
    }
}
