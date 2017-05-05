package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishResult;
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
import com.google.api.server.spi.config.Nullable;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sns",
        canonicalName = "Sns",
        title = "Amazon Simple Notification Service (SNS)",
        description = "Pub/Sub, Mobile Push and SMS",
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
public final class SnsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "topics.list",
            path = "{region}/topics"
    )
    public TopicsResponse topicsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSNS> clientWrapper = new AmazonClientHelper(credentials).getSns(region)) {
            final ListTopicsResult response = clientWrapper.getClient().listTopics(
                    new ListTopicsRequest()
                            .withNextToken(page)
            );
            return new TopicsResponse(response.getTopics(), response.getNextToken());
        } catch (Throwable t) {
            return new TopicsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "topics.attributes.list",
            path = "{region}/topics/TOPIC_ARN/attributes"
    )
    public AttributesResponse topicsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("topicArn") final String topicArn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSNS> clientWrapper = new AmazonClientHelper(credentials).getSns(region)) {
            final Map<String, String> out = clientWrapper.getClient().getTopicAttributes(new GetTopicAttributesRequest(topicArn)).getAttributes();
            return new AttributesResponse(out);
        } catch (Throwable t) {
            return new AttributesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "topics.publish",
            path = "{region}/topics/TOPIC_ARN"
    )
    public PublishResponse publish(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("topicArn") final String topicArn,
            final PublishRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSNS> clientWrapper = new AmazonClientHelper(credentials).getSns(region)) {
            final PublishResult result = clientWrapper.getClient().publish(
                    new com.amazonaws.services.sns.model.PublishRequest()
                            .withTopicArn(topicArn)
                            .withTargetArn(request.getTargetArn())
                            .withPhoneNumber(request.getPhoneNumber())
                            .withMessage(request.getMessage())
                            .withSubject(request.getSubject())
                            .withMessageStructure(request.getMessageStructure())
                            .withMessageAttributes(request.getMessageAttributes())
            );
            return new PublishResponse(result.getMessageId());
        } catch (Throwable t) {
            return new PublishResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subscriptions.list",
            path = "{region}/subscriptions"
    )
    public SubscriptionsResponse subscriptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSNS> clientWrapper = new AmazonClientHelper(credentials).getSns(region)) {
            final ListSubscriptionsResult response = clientWrapper.getClient().listSubscriptions(
                    new ListSubscriptionsRequest()
                            .withNextToken(page)
            );
            return new SubscriptionsResponse(response.getSubscriptions(), response.getNextToken());
        } catch (Throwable t) {
            return new SubscriptionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subscriptions.attributes.list",
            path = "{region}/subscriptions/SUBSCRIPTION_ARN"
    )
    public AttributesResponse subscriptionsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("subscriptionArn") final String subscriptionArn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonSNS> clientWrapper = new AmazonClientHelper(credentials).getSns(region)) {
            final Map<String, String> out = clientWrapper.getClient().getSubscriptionAttributes(new GetSubscriptionAttributesRequest(subscriptionArn)).getAttributes();
            return new AttributesResponse(out);
        } catch (Throwable t) {
            return new AttributesResponse(AmazonResponse.parse(t));
        }
    }
}
