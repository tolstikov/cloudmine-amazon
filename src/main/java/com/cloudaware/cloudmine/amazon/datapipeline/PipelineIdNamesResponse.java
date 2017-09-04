package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.services.datapipeline.model.PipelineIdName;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelineIdNamesResponse extends AmazonResponse {
    private List<PipelineIdName> pipelines;

    public List<PipelineIdName> getPipelines() {
        return pipelines;
    }

    public void setPipelines(final List<PipelineIdName> pipelines) {
        this.pipelines = pipelines;
    }
}
