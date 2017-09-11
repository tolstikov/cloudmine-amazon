package com.cloudaware.cloudmine.amazon.dynamodbstreams;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DynamoDbStreamsCaller {

    private DynamoDbStreamsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonDynamoDBStreams, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonDynamoDBStreams.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDynamoDbStreams(region));
    }
}
