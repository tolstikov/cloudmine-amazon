package com.cloudaware.cloudmine.amazon.kinesisfirehose;

import com.amazonaws.services.kinesisfirehose.model.DescribeDeliveryStreamRequest;
import com.amazonaws.services.kinesisfirehose.model.DescribeDeliveryStreamResult;
import com.amazonaws.services.kinesisfirehose.model.ListDeliveryStreamsRequest;
import com.amazonaws.services.kinesisfirehose.model.ListDeliveryStreamsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "kinesisfirehose",
        canonicalName = "KinesisFirehose",
        title = "Amazon Kinesis Firehose",
        description = "Amazon Kinesis Firehose is a fully managed service that delivers real-time streaming data to destinations",
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
public final class KinesisFirehoseApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deliveryStreamNames.list",
            path = "{region}/delivery-stream-names"
    )
    public DeliveryStreamNamesResponse deliveryStreamNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KinesisFirehoseCaller.get(ListDeliveryStreamsRequest.class, DeliveryStreamNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDeliveryStreamsResult result = client.listDeliveryStreams(request.withExclusiveStartDeliveryStreamName(page));
            response.setDeliveryStreamNames(result.getDeliveryStreamNames());
            if (result.isHasMoreDeliveryStreams()) {
                response.setNextPage(result.getDeliveryStreamNames().get(result.getDeliveryStreamNames().size() - 1));
            }
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deliveryStreams.get",
            path = "{region}/delivery-streams/{deliveryStreamName}"
    )
    public DeliveryStreamResponse deliveryStreamsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("deliveryStreamName") final String deliveryStreamName
    ) throws AmazonUnparsedException {
        return KinesisFirehoseCaller.get(DescribeDeliveryStreamRequest.class, DeliveryStreamResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDeliveryStreamResult result = client.describeDeliveryStream(
                    request
                            .withDeliveryStreamName(deliveryStreamName)
                            .withLimit(10000)
            );
            response.setDeliveryStream(result.getDeliveryStreamDescription());
        });
    }
}
