package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.codestar.AWSCodeStar;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CodeStarCaller {

    private CodeStarCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCodeStar, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCodeStar.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCodeStar(region));
    }
}
