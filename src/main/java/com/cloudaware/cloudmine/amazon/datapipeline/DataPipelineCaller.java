package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.datapipeline.DataPipeline;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class DataPipelineCaller {

    private DataPipelineCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<DataPipeline, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, DataPipeline.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getDataPipeline(region));
    }
}
