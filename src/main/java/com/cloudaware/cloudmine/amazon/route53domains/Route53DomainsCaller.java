package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.route53domains.AmazonRoute53Domains;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class Route53DomainsCaller {

    private Route53DomainsCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonRoute53Domains, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonRoute53Domains.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getRoute53Domains());
    }
}
