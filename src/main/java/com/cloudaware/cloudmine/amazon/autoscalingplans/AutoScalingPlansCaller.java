package com.cloudaware.cloudmine.amazon.autoscalingplans;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.autoscalingplans.AWSAutoScalingPlans;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class AutoScalingPlansCaller {

    private AutoScalingPlansCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSAutoScalingPlans, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSAutoScalingPlans.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAutoScalingPlans(region));
    }
}
