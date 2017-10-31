package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.services.stepfunctions.model.ExecutionListItem;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ExecutionsResponse extends AmazonResponse {

    private List<ExecutionListItem> executions;

    public List<ExecutionListItem> getExecutions() {
        return executions;
    }

    public void setExecutions(final List<ExecutionListItem> executions) {
        this.executions = executions;
    }
}
