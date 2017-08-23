package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.ComplianceByConfigRule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RuleCompliancesResponse extends AmazonResponse {

    private List<ComplianceByConfigRule> ruleCompliances;

    public List<ComplianceByConfigRule> getRuleCompliances() {
        return ruleCompliances;
    }

    public void setRuleCompliances(final List<ComplianceByConfigRule> ruleCompliances) {
        this.ruleCompliances = ruleCompliances;
    }
}
