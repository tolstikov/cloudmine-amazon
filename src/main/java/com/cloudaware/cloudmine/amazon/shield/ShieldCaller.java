package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.shield.AWSShield;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ShieldCaller {

    private ShieldCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSShield, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSShield.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getShield());
    }
}
