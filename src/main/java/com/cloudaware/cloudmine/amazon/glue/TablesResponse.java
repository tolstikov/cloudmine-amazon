package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Table;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TablesResponse extends AmazonResponse {

    private List<Table> tables;

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(final List<Table> tables) {
        this.tables = tables;
    }
}
