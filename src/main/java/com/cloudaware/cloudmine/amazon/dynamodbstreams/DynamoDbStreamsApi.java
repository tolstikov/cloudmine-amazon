package com.cloudaware.cloudmine.amazon.dynamodbstreams;

import com.amazonaws.services.dynamodbv2.model.DescribeStreamRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeStreamResult;
import com.amazonaws.services.dynamodbv2.model.ListStreamsRequest;
import com.amazonaws.services.dynamodbv2.model.ListStreamsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "dynamodbstreams",
        canonicalName = "DynamoDbStreams",
        title = "Amazon DynamoDB Streams",
        description = "Managed NoSQL Database",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE
)
public final class DynamoDbStreamsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streams.list",
            path = "{region}/streams"
    )
    public StreamsResponse streamsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DynamoDbStreamsCaller.get(ListStreamsRequest.class, StreamsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListStreamsResult result = client.listStreams(
                    request.withExclusiveStartStreamArn(page)
            );
            response.setStreams(result.getStreams());
            response.setNextPage(result.getLastEvaluatedStreamArn());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streams.get",
            path = "{region}/streams/STREAM_ARN"
    )
    public StreamResponse streamsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("streamArn") final String streamArn
    ) throws AmazonUnparsedException {
        return DynamoDbStreamsCaller.get(DescribeStreamRequest.class, StreamResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStreamResult result = client.describeStream(
                    request.withStreamArn(streamArn)
            );
            response.setStreamDescription(result.getStreamDescription());
        });
    }
}
