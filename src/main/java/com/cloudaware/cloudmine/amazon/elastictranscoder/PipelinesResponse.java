package com.cloudaware.cloudmine.amazon.elastictranscoder;

import com.amazonaws.services.elastictranscoder.model.Pipeline;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelinesResponse extends AmazonResponse {
    private List<Pipeline> pipelines;

    public List<Pipeline> getPipelines() {
        return pipelines;
    }

    public void setPipelines(final List<Pipeline> pipelines) {
        this.pipelines = pipelines;
    }
}
