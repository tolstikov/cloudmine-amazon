package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.SecurityConfigurationSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SecurityConfigurationsResponse extends AmazonResponse {
    private List<SecurityConfigurationSummary> securityConfigurationSummaries;

    public List<SecurityConfigurationSummary> getSecurityConfigurationSummaries() {
        return securityConfigurationSummaries;
    }

    public void setSecurityConfigurationSummaries(final List<SecurityConfigurationSummary> securityConfigurationSummaries) {
        this.securityConfigurationSummaries = securityConfigurationSummaries;
    }
}
