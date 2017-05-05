package com.cloudaware.cloudmine.amazon.emr;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:33
 */
public final class JobFlowRunResponse extends AmazonResponse {
    private String jobFlowId;

    public JobFlowRunResponse() {
    }

    public JobFlowRunResponse(final AmazonException exception) {
        super(exception);
    }

    public JobFlowRunResponse(final String jobFlowId) {
        this.jobFlowId = jobFlowId;
    }

    public String getJobFlowId() {
        return jobFlowId;
    }

    public void setJobFlowId(final String jobFlowId) {
        this.jobFlowId = jobFlowId;
    }
}
