package com.cloudaware.cloudmine.amazon.elastictranscoder;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoder;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ElasticTranscoderCaller {

    private ElasticTranscoderCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AmazonElasticTranscoder, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AmazonElasticTranscoder.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getElasticTranscoder(region));
    }
}
