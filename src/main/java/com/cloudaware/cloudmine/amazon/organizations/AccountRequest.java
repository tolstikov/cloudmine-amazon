package com.cloudaware.cloudmine.amazon.organizations;

public final class AccountRequest {
    private String email;
    private String accountName;
    private String roleName;
    private String iamUserAccessToBilling;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public String getIamUserAccessToBilling() {
        return iamUserAccessToBilling;
    }

    public void setIamUserAccessToBilling(final String iamUserAccessToBilling) {
        this.iamUserAccessToBilling = iamUserAccessToBilling;
    }
}
