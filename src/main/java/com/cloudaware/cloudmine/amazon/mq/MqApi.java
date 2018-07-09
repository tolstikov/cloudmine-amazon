package com.cloudaware.cloudmine.amazon.mq;

import com.amazonaws.services.mq.model.DescribeBrokerRequest;
import com.amazonaws.services.mq.model.DescribeBrokerResult;
import com.amazonaws.services.mq.model.DescribeConfigurationRevisionRequest;
import com.amazonaws.services.mq.model.DescribeConfigurationRevisionResult;
import com.amazonaws.services.mq.model.DescribeUserRequest;
import com.amazonaws.services.mq.model.DescribeUserResult;
import com.amazonaws.services.mq.model.ListBrokersRequest;
import com.amazonaws.services.mq.model.ListBrokersResult;
import com.amazonaws.services.mq.model.ListConfigurationRevisionsRequest;
import com.amazonaws.services.mq.model.ListConfigurationRevisionsResult;
import com.amazonaws.services.mq.model.ListConfigurationsRequest;
import com.amazonaws.services.mq.model.ListConfigurationsResult;
import com.amazonaws.services.mq.model.ListUsersRequest;
import com.amazonaws.services.mq.model.ListUsersResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "mq",
        canonicalName = "Mq",
        title = "Amazon MQ",
        description = "Amazon MQ is a managed message broker service for Apache ActiveMQ that makes it easy to set up and operate message brokers in the cloud",
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
public final class MqApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "brokers.list",
            path = "{region}/brokers"
    )
    public BrokersResponse brokersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return MqCaller.get(ListBrokersRequest.class, BrokersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListBrokersResult result = client.listBrokers(request.withNextToken(page));
            response.setBrokers(result.getBrokerSummaries());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "brokers.details.get",
            path = "{region}/brokers/{brokerId}/details"
    )
    public BrokerDetailsResponse brokersDetailsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("brokerId") final String brokerId
    ) throws AmazonUnparsedException {
        return MqCaller.get(DescribeBrokerRequest.class, BrokerDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeBrokerResult result = client.describeBroker(request.withBrokerId(brokerId));
            response.setDetails(result);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "brokers.users.list",
            path = "{region}/brokers/{brokerId}/users"
    )
    public UsersResponse brokersUsersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("brokerId") final String brokerId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return MqCaller.get(ListUsersRequest.class, UsersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListUsersResult result = client.listUsers(request.withBrokerId(brokerId).withNextToken(page));
            response.setUsers(result.getUsers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "brokers.users.details.get",
            path = "{region}/brokers/{brokerId}/users/{username}/details"
    )
    public UserDetailsResponse brokersUsersDetailsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("brokerId") final String brokerId,
            @Named("username") final String username
    ) throws AmazonUnparsedException {
        return MqCaller.get(DescribeUserRequest.class, UserDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeUserResult result = client.describeUser(request.withBrokerId(brokerId).withUsername(username));
            response.setDetails(result);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurations.list",
            path = "{region}/configurations"
    )
    public ConfigurationsResponse configurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return MqCaller.get(ListConfigurationsRequest.class, ConfigurationsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListConfigurationsResult result = client.listConfigurations(request.withNextToken(page));
            response.setConfigurations(result.getConfigurations());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurations.revisions.list",
            path = "{region}/configurations/{configurationId}/revisions"
    )
    public ConfigurationRevisionsResponse configurationsRevisionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("configurationId") final String configurationId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return MqCaller.get(ListConfigurationRevisionsRequest.class, ConfigurationRevisionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListConfigurationRevisionsResult result = client.listConfigurationRevisions(request.withConfigurationId(configurationId).withNextToken(page));
            response.setRevisions(result.getRevisions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurations.revisions.details.get",
            path = "{region}/configurations/{configurationId}/revisions/{revisionId}/details"
    )
    public ConfigurationRevisionDetailsResponse configurationsRevisionsDetailsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("configurationId") final String configurationId,
            @Named("revisionId") final String revisionId
    ) throws AmazonUnparsedException {
        return MqCaller.get(DescribeConfigurationRevisionRequest.class, ConfigurationRevisionDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationRevisionResult result = client.describeConfigurationRevision(request.withConfigurationId(configurationId).withConfigurationRevision(revisionId));
            response.setData(result.getData());
        });
    }
}
