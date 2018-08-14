package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.cloudwatchevents.AmazonCloudWatchEvents;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CloudWatchEventsCaller {

    private CloudWatchEventsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCloudWatchEvents, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCloudWatchEvents.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCwEvents(region));
    }
}
