package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.PipelineSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelinesResponse extends AmazonResponse {

    private List<PipelineSummary> pipelines;

    public List<PipelineSummary> getPipelines() {
        return pipelines;
    }

    public void setPipelines(final List<PipelineSummary> pipelines) {
        this.pipelines = pipelines;
    }
}
