package com.cloudaware.cloudmine.amazon.efs;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticfilesystem.AmazonElasticFileSystem;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class EfsCaller {

    private EfsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonElasticFileSystem, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonElasticFileSystem.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getEfs(region));
    }
}
