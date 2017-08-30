package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.Rule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class RuleResponse extends AmazonResponse {
    private Rule rule;

    public Rule getRule() {
        return rule;
    }

    public void setRule(final Rule rule) {
        this.rule = rule;
    }
}
