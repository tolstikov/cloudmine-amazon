package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.LoadBalancerAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:08
 */
public final class LoadBalancerAttributesResponse extends AmazonResponse {

    private List<LoadBalancerAttribute> attributes;

    public List<LoadBalancerAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(final List<LoadBalancerAttribute> attributes) {
        this.attributes = attributes;
    }
}
