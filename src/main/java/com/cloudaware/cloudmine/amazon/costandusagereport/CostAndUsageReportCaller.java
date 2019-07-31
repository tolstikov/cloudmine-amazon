package com.cloudaware.cloudmine.amazon.costandusagereport;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.costandusagereport.AWSCostAndUsageReport;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class CostAndUsageReportCaller {

    private CostAndUsageReportCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSCostAndUsageReport, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSCostAndUsageReport.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getCostAndUsageReport());
    }
}
