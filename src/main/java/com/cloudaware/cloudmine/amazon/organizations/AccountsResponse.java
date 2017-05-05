package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Account;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AccountsResponse extends AmazonResponse {
    private List<Account> accounts;

    public AccountsResponse() {
    }

    public AccountsResponse(final AmazonException excepiton) {
        super(excepiton);
    }

    public AccountsResponse(final List<Account> accounts, final String nextPage) {
        super(nextPage);
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }
}
