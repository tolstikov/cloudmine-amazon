package com.cloudaware.cloudmine.amazon.codestar;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class UserProfileResponse extends AmazonResponse {

    private Date createdTimestamp;
    private String displayName;
    private String emailAddress;
    private Date lastModifiedTimestamp;
    private String sshPublicKey;
    private String userArn;

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(final Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(final Date lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }

    public String getSshPublicKey() {
        return sshPublicKey;
    }

    public void setSshPublicKey(final String sshPublicKey) {
        this.sshPublicKey = sshPublicKey;
    }

    public String getUserArn() {
        return userArn;
    }

    public void setUserArn(final String userArn) {
        this.userArn = userArn;
    }
}
