package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.opsworks.AWSOpsWorks;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class OpsWorksCaller {

    private OpsWorksCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSOpsWorks, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSOpsWorks.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getOpsWorks(region));
    }
}
