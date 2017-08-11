package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.SslPolicy;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SslPoliciesResponse extends AmazonResponse {

    private List<SslPolicy> sslPolicies;

    public List<SslPolicy> getSslPolicies() {
        return sslPolicies;
    }

    public void setSslPolicies(final List<SslPolicy> sslPolicies) {
        this.sslPolicies = sslPolicies;
    }
}
