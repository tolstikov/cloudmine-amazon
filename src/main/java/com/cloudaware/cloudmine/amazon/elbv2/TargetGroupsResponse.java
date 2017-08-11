package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.TargetGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:08
 */
public final class TargetGroupsResponse extends AmazonResponse {

    private List<TargetGroup> targetGroups;

    public List<TargetGroup> getTargetGroups() {
        return targetGroups;
    }

    public void setTargetGroups(final List<TargetGroup> targetGroups) {
        this.targetGroups = targetGroups;
    }
}
