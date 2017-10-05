package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.StageState;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;
import java.util.List;

public final class PipelineStateResponse extends AmazonResponse {

    private Date created;
    private String pipelineName;
    private Integer pipelineVersion;
    private List<StageState> stageStates;
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(final String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public Integer getPipelineVersion() {
        return pipelineVersion;
    }

    public void setPipelineVersion(final Integer pipelineVersion) {
        this.pipelineVersion = pipelineVersion;
    }

    public List<StageState> getStageStates() {
        return stageStates;
    }

    public void setStageStates(final List<StageState> stageStates) {
        this.stageStates = stageStates;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }
}
