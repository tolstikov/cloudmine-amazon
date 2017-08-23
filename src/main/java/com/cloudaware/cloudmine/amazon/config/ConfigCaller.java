package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.config.AmazonConfig;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ConfigCaller {

    private ConfigCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonConfig, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonConfig.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getConfig(region));
    }
}
