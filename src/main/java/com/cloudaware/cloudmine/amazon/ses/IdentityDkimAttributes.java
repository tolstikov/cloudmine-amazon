package com.cloudaware.cloudmine.amazon.ses;

import java.util.List;

public final class IdentityDkimAttributes {

    private String identityName;
    private Boolean dkimEnabled;
    private String dkimVerificationStatus;
    private List<String> dkimTokens;

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(final String identityName) {
        this.identityName = identityName;
    }

    public Boolean getDkimEnabled() {
        return dkimEnabled;
    }

    public void setDkimEnabled(final Boolean dkimEnabled) {
        this.dkimEnabled = dkimEnabled;
    }

    public String getDkimVerificationStatus() {
        return dkimVerificationStatus;
    }

    public void setDkimVerificationStatus(final String dkimVerificationStatus) {
        this.dkimVerificationStatus = dkimVerificationStatus;
    }

    public List<String> getDkimTokens() {
        return dkimTokens;
    }

    public void setDkimTokens(final List<String> dkimTokens) {
        this.dkimTokens = dkimTokens;
    }
}
