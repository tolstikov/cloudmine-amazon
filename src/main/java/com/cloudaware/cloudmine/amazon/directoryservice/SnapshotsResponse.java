package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.Snapshot;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SnapshotsResponse extends AmazonResponse {
    private List<Snapshot> snapshots;

    public SnapshotsResponse() {
    }

    public SnapshotsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public SnapshotsResponse(final List<Snapshot> snapshots, final String nextPage) {
        super(nextPage);
        this.snapshots = snapshots;
    }

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(final List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }
}
