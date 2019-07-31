package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AccountAliasesResponse extends AmazonResponse {
    private List<String> accountAliases;

    public List<String> getAccountAliases() {
        return accountAliases;
    }

    public AccountAliasesResponse setAccountAliases(final List<String> accountAliases) {
        this.accountAliases = accountAliases;
        return this;
    }
}
