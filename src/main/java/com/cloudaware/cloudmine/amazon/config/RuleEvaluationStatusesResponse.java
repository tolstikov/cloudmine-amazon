package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.ConfigRuleEvaluationStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RuleEvaluationStatusesResponse extends AmazonResponse {

    private List<ConfigRuleEvaluationStatus> ruleEvaluationStatuses;

    public List<ConfigRuleEvaluationStatus> getRuleEvaluationStatuses() {
        return ruleEvaluationStatuses;
    }

    public void setRuleEvaluationStatuses(final List<ConfigRuleEvaluationStatus> ruleEvaluationStatuses) {
        this.ruleEvaluationStatuses = ruleEvaluationStatuses;
    }
}
