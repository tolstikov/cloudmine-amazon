package com.cloudaware.cloudmine.amazon.ses;

public final class IdentityVerificationAttributes {

    private String identityName;
    private String verificationStatus;
    private String verificationToken;

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(final String identityName) {
        this.identityName = identityName;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(final String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(final String verificationToken) {
        this.verificationToken = verificationToken;
    }
}
