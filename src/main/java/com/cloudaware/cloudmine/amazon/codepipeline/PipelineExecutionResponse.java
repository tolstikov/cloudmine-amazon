package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.PipelineExecution;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class PipelineExecutionResponse extends AmazonResponse {

    private PipelineExecution pipelineExecution;

    public PipelineExecution getPipelineExecution() {
        return pipelineExecution;
    }

    public void setPipelineExecution(final PipelineExecution pipelineExecution) {
        this.pipelineExecution = pipelineExecution;
    }
}
