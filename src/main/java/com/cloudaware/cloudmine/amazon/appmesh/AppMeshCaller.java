package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.appmesh.AWSAppMesh;
import com.amazonaws.services.appmesh.AWSAppMeshClient;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class AppMeshCaller {

    private AppMeshCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSAppMesh, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSAppMeshClient.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getAwsAppMesh(region));
    }
}
