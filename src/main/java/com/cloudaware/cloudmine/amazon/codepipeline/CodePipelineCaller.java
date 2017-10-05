package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.codepipeline.AWSCodePipeline;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CodePipelineCaller {

    private CodePipelineCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCodePipeline, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCodePipeline.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCodePipeline(region));
    }
}
