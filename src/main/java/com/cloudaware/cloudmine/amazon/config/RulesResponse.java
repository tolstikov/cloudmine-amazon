package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.ConfigRule;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RulesResponse extends AmazonResponse {

    private List<ConfigRule> rules;

    public List<ConfigRule> getRules() {
        return rules;
    }

    public void setRules(final List<ConfigRule> rules) {
        this.rules = rules;
    }
}
