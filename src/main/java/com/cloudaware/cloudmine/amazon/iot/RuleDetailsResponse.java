package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.TopicRule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RuleDetailsResponse extends AmazonResponse {

    private TopicRule rule;
    private String ruleArn;

    public void setRule(final TopicRule rule) {
        this.rule = rule;
    }

    public TopicRule getRule() {
        return rule;
    }

    public void setRuleArn(final String ruleArn) {
        this.ruleArn = ruleArn;
    }

    public String getRuleArn() {
        return ruleArn;
    }
}
