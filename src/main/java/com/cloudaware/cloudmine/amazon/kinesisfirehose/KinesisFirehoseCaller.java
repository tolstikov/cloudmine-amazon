package com.cloudaware.cloudmine.amazon.kinesisfirehose;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class KinesisFirehoseCaller {

    private KinesisFirehoseCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonKinesisFirehose, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonKinesisFirehose.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getKinesisFirehose(region));
    }
}
