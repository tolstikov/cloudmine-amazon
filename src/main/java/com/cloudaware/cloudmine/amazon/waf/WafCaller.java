package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.waf.AWSWAF;
import com.amazonaws.services.waf.AWSWAFRegional;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class WafCaller {

    private WafCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSWAF, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSWAF.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getWaf());
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSWAFRegional, RqT, RsT> getRegional(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSWAFRegional.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getWafRegional(region));
    }
}
