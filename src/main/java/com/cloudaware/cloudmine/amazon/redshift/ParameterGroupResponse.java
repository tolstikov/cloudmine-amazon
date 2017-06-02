package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.ClusterParameterGroup;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ParameterGroupResponse extends AmazonResponse {

    private List<ClusterParameterGroup> parameterGroups;

    public ParameterGroupResponse() {

    }

    public ParameterGroupResponse(final AmazonException exception) {
        super(exception);
    }

    public ParameterGroupResponse(final List<ClusterParameterGroup> parameterGroups, final String nextPage) {
        super(nextPage);
        this.parameterGroups = parameterGroups;
    }

    public List<ClusterParameterGroup> getParameterGroups() {
        return parameterGroups;
    }

    public void setParameterGroups(final List<ClusterParameterGroup> parameterGroups) {
        this.parameterGroups = parameterGroups;
    }
}
