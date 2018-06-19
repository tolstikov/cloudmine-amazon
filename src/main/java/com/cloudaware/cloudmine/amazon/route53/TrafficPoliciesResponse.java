package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.TrafficPolicySummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrafficPoliciesResponse extends AmazonResponse {

    private List<TrafficPolicySummary> trafficPolicies;

    public List<TrafficPolicySummary> getTrafficPolicies() {
        return trafficPolicies;
    }

    public void setTrafficPolicies(final List<TrafficPolicySummary> trafficPolicies) {
        this.trafficPolicies = trafficPolicies;
    }
}
