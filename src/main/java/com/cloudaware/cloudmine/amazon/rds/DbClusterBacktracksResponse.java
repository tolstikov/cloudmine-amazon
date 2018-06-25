package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBClusterBacktrack;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DbClusterBacktracksResponse extends AmazonResponse {

    private List<DBClusterBacktrack> dbClusterBacktracks;

    public List<DBClusterBacktrack> getDbClusterBacktracks() {
        return dbClusterBacktracks;
    }

    public void setDbClusterBacktracks(final List<DBClusterBacktrack> dbClusterBacktracks) {
        this.dbClusterBacktracks = dbClusterBacktracks;
    }
}
