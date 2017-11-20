package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Database;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DatabasesResponse extends AmazonResponse {

    private List<Database> databases;

    public List<Database> getDatabases() {
        return databases;
    }

    public void setDatabases(final List<Database> databases) {
        this.databases = databases;
    }
}
