package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ReceiptRule;
import com.amazonaws.services.simpleemail.model.ReceiptRuleSetMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ActiveReceiptRuleSetResponse extends AmazonResponse {

    private ReceiptRuleSetMetadata receiptRuleSetMetadata;
    private List<ReceiptRule> receiptRules;

    public ReceiptRuleSetMetadata getReceiptRuleSetMetadata() {
        return receiptRuleSetMetadata;
    }

    public void setReceiptRuleSetMetadata(final ReceiptRuleSetMetadata receiptRuleSetMetadata) {
        this.receiptRuleSetMetadata = receiptRuleSetMetadata;
    }

    public List<ReceiptRule> getReceiptRules() {
        return receiptRules;
    }

    public void setReceiptRules(final List<ReceiptRule> receiptRules) {
        this.receiptRules = receiptRules;
    }
}
