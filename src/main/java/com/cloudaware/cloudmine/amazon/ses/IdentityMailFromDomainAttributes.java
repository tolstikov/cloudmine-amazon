package com.cloudaware.cloudmine.amazon.ses;

public final class IdentityMailFromDomainAttributes {

    private String identityName;
    private String mailFromDomain;
    private String mailFromDomainStatus;
    private String behaviorOnMXFailure;

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(final String identityName) {
        this.identityName = identityName;
    }

    public String getMailFromDomain() {
        return mailFromDomain;
    }

    public void setMailFromDomain(final String mailFromDomain) {
        this.mailFromDomain = mailFromDomain;
    }

    public String getMailFromDomainStatus() {
        return mailFromDomainStatus;
    }

    public void setMailFromDomainStatus(final String mailFromDomainStatus) {
        this.mailFromDomainStatus = mailFromDomainStatus;
    }

    public String getBehaviorOnMXFailure() {
        return behaviorOnMXFailure;
    }

    public void setBehaviorOnMXFailure(final String behaviorOnMXFailure) {
        this.behaviorOnMXFailure = behaviorOnMXFailure;
    }
}
