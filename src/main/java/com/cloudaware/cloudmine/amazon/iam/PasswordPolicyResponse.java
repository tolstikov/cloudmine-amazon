package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.PasswordPolicy;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 05:06
 */
public final class PasswordPolicyResponse extends AmazonResponse {
    private PasswordPolicy passwordPolicy;

    public PasswordPolicyResponse() {
    }

    public PasswordPolicyResponse(final AmazonException exception) {
        super(exception);
    }

    public PasswordPolicyResponse(final PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicy;
    }

    public void setPasswordPolicy(final PasswordPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }
}
