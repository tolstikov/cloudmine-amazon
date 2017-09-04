package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.services.datapipeline.model.PipelineObject;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelineObjectsResponse extends AmazonResponse {
    private List<PipelineObject> pipelineObjects;

    public List<PipelineObject> getPipelineObjects() {
        return pipelineObjects;
    }

    public void setPipelineObjects(final List<PipelineObject> pipelineObjects) {
        this.pipelineObjects = pipelineObjects;
    }
}
