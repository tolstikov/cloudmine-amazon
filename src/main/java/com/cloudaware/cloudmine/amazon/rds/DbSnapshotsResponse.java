package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBSnapshot;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:22
 */
public final class DbSnapshotsResponse extends AmazonResponse {
    private List<DBSnapshot> dbSnapshots;

    public DbSnapshotsResponse() {
    }

    public DbSnapshotsResponse(final AmazonException exception) {
        super(exception);
    }

    public DbSnapshotsResponse(final List<DBSnapshot> dbSnapshots, final String nextPage) {
        super(nextPage);
        this.dbSnapshots = dbSnapshots;
    }

    public List<DBSnapshot> getDbSnapshots() {
        return dbSnapshots;
    }

    public void setDbSnapshots(final List<DBSnapshot> dbSnapshots) {
        this.dbSnapshots = dbSnapshots;
    }
}
