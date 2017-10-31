package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class StepFunctionsCaller {

    private StepFunctionsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSStepFunctions, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSStepFunctions.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getStepFunctions(region));
    }
}
