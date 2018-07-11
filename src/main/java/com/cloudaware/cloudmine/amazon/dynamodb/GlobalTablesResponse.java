package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.GlobalTable;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class GlobalTablesResponse extends AmazonResponse {

    private List<GlobalTable> globalTables;

    public List<GlobalTable> getGlobalTables() {
        return globalTables;
    }

    public void setGlobalTables(final List<GlobalTable> globalTables) {
        this.globalTables = globalTables;
    }
}
