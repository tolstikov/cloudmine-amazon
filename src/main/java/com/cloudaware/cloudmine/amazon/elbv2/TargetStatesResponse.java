package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.TargetHealthDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TargetStatesResponse extends AmazonResponse {

    private List<TargetHealthDescription> targetStates;

    public List<TargetHealthDescription> getTargetStates() {
        return targetStates;
    }

    public void setTargetStates(final List<TargetHealthDescription> targetStates) {
        this.targetStates = targetStates;
    }
}
