package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProtfolioAccessResponse extends AmazonResponse {
    private List<String> accountIds;

    public List<String> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(final List<String> accountIds) {
        this.accountIds = accountIds;
    }
}
