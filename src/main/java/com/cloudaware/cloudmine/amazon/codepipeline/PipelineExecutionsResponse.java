package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.PipelineExecutionSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelineExecutionsResponse extends AmazonResponse {

    private List<PipelineExecutionSummary> pipelineExecutions;

    public List<PipelineExecutionSummary> getPipelineExecutions() {
        return pipelineExecutions;
    }

    public void setPipelineExecutions(final List<PipelineExecutionSummary> pipelineExecutions) {
        this.pipelineExecutions = pipelineExecutions;
    }
}
