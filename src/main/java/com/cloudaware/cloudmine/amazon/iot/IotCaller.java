package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.iot.AWSIot;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class IotCaller {

    private IotCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSIot, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSIot.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getIot(region));
    }
}
