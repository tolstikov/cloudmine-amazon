package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBClusterSnapshot;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:24
 */
public final class DbClusterSnapshotsResponse extends AmazonResponse {
    private List<DBClusterSnapshot> dbClusterSnapshots;

    public DbClusterSnapshotsResponse() {
    }

    public DbClusterSnapshotsResponse(final AmazonException exception) {
        super(exception);
    }

    public DbClusterSnapshotsResponse(final List<DBClusterSnapshot> dbClusterSnapshots, final String nextPage) {
        super(nextPage);
        this.dbClusterSnapshots = dbClusterSnapshots;
    }

    public List<DBClusterSnapshot> getDbClusterSnapshots() {
        return dbClusterSnapshots;
    }

    public void setDbClusterSnapshots(final List<DBClusterSnapshot> dbClusterSnapshots) {
        this.dbClusterSnapshots = dbClusterSnapshots;
    }
}
