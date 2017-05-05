package com.cloudaware.cloudmine.amazon.dynamodb;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:24
 */
public final class TableNamesResponse extends AmazonResponse {
    private List<String> tableNames;

    public TableNamesResponse() {
    }

    public TableNamesResponse(final AmazonException exception) {
        super(exception);
    }

    public TableNamesResponse(final List<String> tableNames, final String nextPage) {
        super(nextPage);
        this.tableNames = tableNames;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(final List<String> tableNames) {
        this.tableNames = tableNames;
    }
}
