package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.services.datapipeline.model.PipelineDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelinesResponse extends AmazonResponse {
    private List<PipelineDescription> pipelineDescriptions;

    public List<PipelineDescription> getPipelineDescriptions() {
        return pipelineDescriptions;
    }

    public void setPipelineDescriptions(final List<PipelineDescription> pipelineDescriptions) {
        this.pipelineDescriptions = pipelineDescriptions;
    }
}
