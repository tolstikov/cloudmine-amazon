package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.services.codecommit.model.BranchInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class BranchResponse extends AmazonResponse {

    private BranchInfo branch;

    public BranchInfo getBranch() {
        return branch;
    }

    public void setBranch(final BranchInfo branch) {
        this.branch = branch;
    }
}
