package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.PipelineDeclaration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class PipelineResponse extends AmazonResponse {

    private PipelineDeclaration pipeline;

    public PipelineDeclaration getPipeline() {
        return pipeline;
    }

    public void setPipeline(final PipelineDeclaration pipeline) {
        this.pipeline = pipeline;
    }
}
