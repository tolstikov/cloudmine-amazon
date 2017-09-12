package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.services.dax.model.ParameterGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ParameterGroupsResponse extends AmazonResponse {
    private List<ParameterGroup> parameterGroups;

    public List<ParameterGroup> getParameterGroups() {
        return parameterGroups;
    }

    public void setParameterGroups(final List<ParameterGroup> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }
}
