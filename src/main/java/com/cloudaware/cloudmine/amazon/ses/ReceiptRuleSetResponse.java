package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ReceiptRule;
import com.amazonaws.services.simpleemail.model.ReceiptRuleSetMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ReceiptRuleSetResponse extends AmazonResponse {

    private ReceiptRuleSetMetadata metadata;
    private List<ReceiptRule> rules;

    public ReceiptRuleSetMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(final ReceiptRuleSetMetadata metadata) {
        this.metadata = metadata;
    }

    public List<ReceiptRule> getRules() {
        return rules;
    }

    public void setRules(final List<ReceiptRule> rules) {
        this.rules = rules;
    }
}
