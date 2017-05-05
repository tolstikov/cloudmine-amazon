package com.cloudaware.cloudmine.amazon.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.ClientWrapper;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sqs",
        canonicalName = "Sqs",
        title = "Amazon Simple Queue Service (SQS)",
        description = "Managed Message Queues",
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
public final class SqsApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queues.list",
            path = "{region}/queues"
    )
    public QueuesResponse queuesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSQS> clientWrapper = new AmazonClientHelper(credentials).getSqs(region)) {
            final List<String> out = clientWrapper.getClient().listQueues().getQueueUrls();
            return new QueuesResponse(out);
        } catch (Throwable t) {
            return new QueuesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queues.attributes.list",
            path = "{region}/queues/QUEUE_URL/attributes"
    )
    public com.cloudaware.cloudmine.amazon.sns.AttributesResponse queuesAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("queueUrl") final String queueUrl
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSQS> clientWrapper = new AmazonClientHelper(credentials).getSqs(region)) {
            final Map<String, String> out = clientWrapper.getClient().getQueueAttributes(new GetQueueAttributesRequest(queueUrl).withAttributeNames("All")).getAttributes();
            return new com.cloudaware.cloudmine.amazon.sns.AttributesResponse(out);
        } catch (Throwable t) {
            return new com.cloudaware.cloudmine.amazon.sns.AttributesResponse(AmazonResponse.parse(t));
        }
    }
}
