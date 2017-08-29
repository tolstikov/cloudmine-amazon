package com.cloudaware.cloudmine.amazon.kinesisanalytics;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalytics;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class KinesisAnalyticsCaller {

    private KinesisAnalyticsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonKinesisAnalytics, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonKinesisAnalytics.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getKinesisAnalytics(region));
    }
}
