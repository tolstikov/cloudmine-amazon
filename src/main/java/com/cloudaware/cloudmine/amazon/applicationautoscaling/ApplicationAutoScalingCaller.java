package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.applicationautoscaling.AWSApplicationAutoScaling;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ApplicationAutoScalingCaller {

    private ApplicationAutoScalingCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSApplicationAutoScaling, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSApplicationAutoScaling.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getApplicationAutoScaling(region));
    }
}
