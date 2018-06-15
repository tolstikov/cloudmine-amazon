package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.Parameter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClusterParametersResponse extends AmazonResponse {

    private List<Parameter> parameters;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
