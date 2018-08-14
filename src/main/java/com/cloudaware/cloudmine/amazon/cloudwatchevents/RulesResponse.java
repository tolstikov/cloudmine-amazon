package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.amazonaws.services.cloudwatchevents.model.Rule;
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
