package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.mq.AmazonMQ;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class MqCaller {

    private MqCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonMQ, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonMQ.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getMq(region));
    }
}
