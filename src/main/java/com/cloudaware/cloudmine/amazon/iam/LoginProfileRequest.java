package com.cloudaware.cloudmine.amazon.iam;

/**
 * User: urmuzov
 * Date: 1/13/14
 * Time: 7:51 PM
 */
public final class LoginProfileRequest {
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
