package com.cloudaware.cloudmine.amazon.efs;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SecurityGroupsResponse extends AmazonResponse {

    private List<String> securityGroups;

    public List<String> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(final List<String> securityGroups) {
        this.securityGroups = securityGroups;
    }
}
