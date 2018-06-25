package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.Parameter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DbClusterParametersResponse extends AmazonResponse {

    private List<Parameter> dbClusterParameters;

    public List<Parameter> getDbClusterParameters() {
        return dbClusterParameters;
    }

    public void setDbClusterParameters(final List<Parameter> dbClusterParameters) {
        this.dbClusterParameters = dbClusterParameters;
    }
}
