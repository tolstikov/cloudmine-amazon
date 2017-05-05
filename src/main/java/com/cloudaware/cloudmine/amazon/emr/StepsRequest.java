package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.StepConfig;

import java.util.List;

public final class StepsRequest {
    private List<StepConfig> stepConfigs;

    public List<StepConfig> getStepConfigs() {
        return stepConfigs;
    }

    public void setStepConfigs(final List<StepConfig> stepConfigs) {
        this.stepConfigs = stepConfigs;
    }
}
