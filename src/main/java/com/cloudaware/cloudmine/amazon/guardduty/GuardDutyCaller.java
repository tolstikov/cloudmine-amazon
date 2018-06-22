package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.guardduty.AmazonGuardDuty;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class GuardDutyCaller {

    private GuardDutyCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonGuardDuty, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonGuardDuty.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getGuardDuty(region));
    }
}
