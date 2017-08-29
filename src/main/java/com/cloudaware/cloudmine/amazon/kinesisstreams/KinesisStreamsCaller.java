package com.cloudaware.cloudmine.amazon.kinesisstreams;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class KinesisStreamsCaller {

    private KinesisStreamsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonKinesis, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonKinesis.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getKinesisStreams(region));
    }
}
