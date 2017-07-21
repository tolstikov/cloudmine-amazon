package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.services.elasticsearch.AWSElasticsearch;
import com.cloudaware.cloudmine.amazon.AmazonCaller;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

final class ElasticsearchCaller {

    private ElasticsearchCaller() {
    }

    public static <RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> AmazonCaller<AWSElasticsearch, RqT, RsT> get(
            final Class<RqT> requestClass,
            final Class<RsT> responseClass,
            final String credentials,
            final String region
    ) {
        return new AmazonCaller<>(requestClass, responseClass, AWSElasticsearch.ENDPOINT_PREFIX, () -> new AmazonClientHelper(credentials).getElasticsearch(region));
    }
}
