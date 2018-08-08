package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.appstream.AmazonAppStream;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class AppStreamCaller {

    private AppStreamCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonAppStream, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonAppStream.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAmazonAppStream(region));
    }
}
