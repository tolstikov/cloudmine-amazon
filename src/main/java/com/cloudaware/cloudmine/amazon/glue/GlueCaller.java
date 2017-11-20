package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.glue.AWSGlue;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class GlueCaller {

    private GlueCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSGlue, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSGlue.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getGlue(region));
    }
}
