package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.LoginProfile;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:54
 */
public final class LoginProfileResponse extends AmazonResponse {
    private LoginProfile loginProfile;

    public LoginProfileResponse() {
    }

    public LoginProfileResponse(final AmazonException exception) {
        super(exception);
    }

    public LoginProfileResponse(final LoginProfile loginProfile) {
        this.loginProfile = loginProfile;
    }

    public LoginProfile getLoginProfile() {

        return loginProfile;
    }

    public void setLoginProfile(final LoginProfile loginProfile) {
        this.loginProfile = loginProfile;
    }
}
