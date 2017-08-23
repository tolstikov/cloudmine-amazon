package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.servicecatalog.AWSServiceCatalog;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ServiceCatalogCaller {

    private ServiceCatalogCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSServiceCatalog, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSServiceCatalog.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getServiceCatalog(region));
    }
}
