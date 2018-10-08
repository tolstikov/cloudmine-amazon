package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.PasswordPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AccountPasswordPolicyResponse extends AmazonResponse {
    private PasswordPolicy passwordPolicy;

    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicy;
    }

    public void setPasswordPolicy(final PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }
}
