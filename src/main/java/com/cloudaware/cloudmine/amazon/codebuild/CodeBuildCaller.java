package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.codebuild.AWSCodeBuild;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CodeBuildCaller {

    private CodeBuildCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCodeBuild, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCodeBuild.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCodeBuild(region));
    }
}
