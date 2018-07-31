package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ReceiptRuleSetMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ReceiptRuleSetsResponse extends AmazonResponse {

    private List<ReceiptRuleSetMetadata> receiptRuleSets;

    public List<ReceiptRuleSetMetadata> getReceiptRuleSets() {
        return receiptRuleSets;
    }

    public void setReceiptRuleSets(final List<ReceiptRuleSetMetadata> receiptRuleSets) {
        this.receiptRuleSets = receiptRuleSets;
    }
}
