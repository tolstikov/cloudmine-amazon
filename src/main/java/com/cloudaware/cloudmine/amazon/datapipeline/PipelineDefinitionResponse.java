package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.services.datapipeline.model.PipelineObject;
import com.amazonaws.services.datapipeline.model.ParameterObject;
import com.amazonaws.services.datapipeline.model.ParameterValue;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class PipelineDefinitionResponse extends AmazonResponse {
    private List<PipelineObject> pipelineObjects;
    private List<ParameterObject> parameterObjects;
    private List<ParameterValue> parameterValues;

    public List<PipelineObject> getPipelineObjects() {
        return pipelineObjects;
    }

    public void setPipelineObjects(final List<PipelineObject> pipelineObjects) {
        this.pipelineObjects = pipelineObjects;
    }

    public List<ParameterObject> getParameterObjects() {
        return parameterObjects;
    }

    public void setParameterObjects(final List<ParameterObject> parameterObjects) {
        this.parameterObjects = parameterObjects;
    }

    public List<ParameterValue> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(final List<ParameterValue> parameterValues) {
        this.parameterValues = parameterValues;
    }
}
