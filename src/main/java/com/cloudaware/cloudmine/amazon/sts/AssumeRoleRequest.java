package com.cloudaware.cloudmine.amazon.sts;

public final class AssumeRoleRequest {
    private String roleArn;
    private String roleSessionName;
    private String policy;
    private Integer durationSeconds;
    private String externalId;
    private String serialNumber;
    private String tokenCode;

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(final String roleArn) {
        this.roleArn = roleArn;
    }

    public String getRoleSessionName() {
        return roleSessionName;
    }

    public void setRoleSessionName(final String roleSessionName) {
        this.roleSessionName = roleSessionName;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(final String policy) {
        this.policy = policy;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(final Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(final String tokenCode) {
        this.tokenCode = tokenCode;
    }
}
