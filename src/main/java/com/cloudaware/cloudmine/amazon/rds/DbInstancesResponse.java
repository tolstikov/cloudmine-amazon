package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBInstance;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:09
 */
public final class DbInstancesResponse extends AmazonResponse {
    private List<DBInstance> dbInstances;

    public DbInstancesResponse() {
    }

    public DbInstancesResponse(final AmazonException exception) {
        super(exception);
    }

    public DbInstancesResponse(final List<DBInstance> dbInstances, final String nextPage) {
        super(nextPage);
        this.dbInstances = dbInstances;
    }

    public List<DBInstance> getDbInstances() {
        return dbInstances;
    }

    public void setDbInstances(final List<DBInstance> dbInstances) {
        this.dbInstances = dbInstances;
    }
}
