package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.Rule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RulesResponse extends AmazonResponse {

    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(final List<Rule> rules) {
        this.rules = rules;
    }
}
