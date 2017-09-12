package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.TopicRuleListItem;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RulesResponse extends AmazonResponse {

    private List<TopicRuleListItem> rules;

    public void setRules(final List<TopicRuleListItem> rules) {
        this.rules = rules;
    }

    public List<TopicRuleListItem> getRules() {
        return rules;
    }
}
