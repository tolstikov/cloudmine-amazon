package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.DirectoryLimits;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DirectoryLimitsResponse extends AmazonResponse {
    private DirectoryLimits directoryLimits;

    public DirectoryLimitsResponse() {
    }

    public DirectoryLimitsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public DirectoryLimitsResponse(final DirectoryLimits directoryLimits) {
        this.directoryLimits = directoryLimits;
    }

    public DirectoryLimits getDirectoryLimits() {
        return directoryLimits;
    }

    public void setDirectoryLimits(final DirectoryLimits directoryLimits) {
        this.directoryLimits = directoryLimits;
    }
}
