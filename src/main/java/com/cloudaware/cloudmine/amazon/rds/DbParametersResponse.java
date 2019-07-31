package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.Parameter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DbParametersResponse extends AmazonResponse {

    private List<Parameter> dbParameters;

    public List<Parameter> getDbParameters() {
        return dbParameters;
    }

    public void setDbParameters(final List<Parameter> dbParameters) {
        this.dbParameters = dbParameters;
    }
}
