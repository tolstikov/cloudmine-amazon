package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.ReceiptFilter;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ReceiptFiltersResponse extends AmazonResponse {

    private List<ReceiptFilter> receiptFilters;

    public List<ReceiptFilter> getReceiptFilters() {
        return receiptFilters;
    }

    public void setReceiptFilters(final List<ReceiptFilter> receiptFilters) {
        this.receiptFilters = receiptFilters;
    }
}
