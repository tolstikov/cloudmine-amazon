package com.cloudaware.cloudmine.amazon.dynamodbaccelerator;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.dax.AmazonDax;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DynamoDbAcceleratorCaller {

    private DynamoDbAcceleratorCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonDax, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonDax.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDynamoDbAccelerator(region));
    }
}
