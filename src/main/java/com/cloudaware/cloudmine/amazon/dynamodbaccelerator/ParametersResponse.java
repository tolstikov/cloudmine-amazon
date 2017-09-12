package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.Parameter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ParametersResponse extends AmazonResponse {
    private List<Parameter> parameters;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
