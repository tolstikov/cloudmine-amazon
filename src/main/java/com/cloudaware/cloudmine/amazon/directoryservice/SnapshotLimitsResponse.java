package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.SnapshotLimits;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class SnapshotLimitsResponse extends AmazonResponse {
    private SnapshotLimits snapshotLimits;

    public SnapshotLimitsResponse() {
    }

    public SnapshotLimitsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public SnapshotLimitsResponse(final SnapshotLimits snapshotLimits) {
        this.snapshotLimits = snapshotLimits;
    }

    public SnapshotLimits getSnapshotLimits() {
        return snapshotLimits;
    }

    public void setSnapshotLimits(final SnapshotLimits snapshotLimits) {
        this.snapshotLimits = snapshotLimits;
    }
}
