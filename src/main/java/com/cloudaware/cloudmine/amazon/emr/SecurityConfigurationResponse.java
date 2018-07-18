package com.cloudaware.cloudmine.amazon.emr;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

public final class SecurityConfigurationResponse extends AmazonResponse {
    private String name;
    private String securityConfiguration;
    private Date creationDateTime;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSecurityConfiguration() {
        return securityConfiguration;
    }

    public void setSecurityConfiguration(final String securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(final Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
