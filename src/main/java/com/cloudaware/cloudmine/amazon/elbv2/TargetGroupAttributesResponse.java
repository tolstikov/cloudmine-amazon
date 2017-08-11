package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroupAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:08
 */
public final class TargetGroupAttributesResponse extends AmazonResponse {

    private List<TargetGroupAttribute> attributes;

    public List<TargetGroupAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(final List<TargetGroupAttribute> attributes) {
        this.attributes = attributes;
    }
}
