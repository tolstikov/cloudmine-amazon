package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.codedeploy.AmazonCodeDeploy;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CodeDeployCaller {

    private CodeDeployCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonCodeDeploy, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonCodeDeploy.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCodeDeploy(region));
    }
}
