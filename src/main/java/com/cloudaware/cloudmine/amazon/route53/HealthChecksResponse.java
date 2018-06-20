package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.HealthCheck;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class HealthChecksResponse extends AmazonResponse {

    private List<HealthCheck> healthChecks;

    public List<HealthCheck> getHealthChecks() {
        return healthChecks;
    }

    public void setHealthChecks(final List<HealthCheck> healthChecks) {
        this.healthChecks = healthChecks;
    }
}
