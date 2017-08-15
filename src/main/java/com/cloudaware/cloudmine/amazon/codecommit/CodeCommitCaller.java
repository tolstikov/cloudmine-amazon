package com.cloudaware.cloudmine.amazon.codecommit;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.codecommit.AWSCodeCommit;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CodeCommitCaller {

    private CodeCommitCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCodeCommit, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCodeCommit.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCodeCommit(region));
    }
}
