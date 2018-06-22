package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.CreateTagsRequest;
import com.amazonaws.services.redshift.model.DeleteTagsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterParameterGroupsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterParameterGroupsResult;
import com.amazonaws.services.redshift.model.DescribeClusterParametersRequest;
import com.amazonaws.services.redshift.model.DescribeClusterParametersResult;
import com.amazonaws.services.redshift.model.DescribeClusterSecurityGroupsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSecurityGroupsResult;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsResult;
import com.amazonaws.services.redshift.model.DescribeClusterSubnetGroupsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSubnetGroupsResult;
import com.amazonaws.services.redshift.model.DescribeClustersRequest;
import com.amazonaws.services.redshift.model.DescribeClustersResult;
import com.amazonaws.services.redshift.model.DescribeEventSubscriptionsRequest;
import com.amazonaws.services.redshift.model.DescribeEventSubscriptionsResult;
import com.amazonaws.services.redshift.model.DescribeEventsRequest;
import com.amazonaws.services.redshift.model.DescribeEventsResult;
import com.amazonaws.services.redshift.model.DescribeLoggingStatusRequest;
import com.amazonaws.services.redshift.model.DescribeLoggingStatusResult;
import com.amazonaws.services.redshift.model.DescribeReservedNodesRequest;
import com.amazonaws.services.redshift.model.DescribeReservedNodesResult;
import com.amazonaws.services.redshift.model.DescribeTagsRequest;
import com.amazonaws.services.redshift.model.DescribeTagsResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

@Api(
        name = "redshift",
        canonicalName = "Redshift",
        title = "Amazon Redshift",
        description = "Fast, Simple, Cost-Effective Data Warehousing",
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
public final class RedshiftApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClustersRequest.class, ClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClustersResult result = client.describeClusters(request.withMarker(page));
            response.setClusters(result.getClusters());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.loggingStatus.get",
            path = "{region}/clusters/{clusterIdentifier}/logging-status"
    )
    public LoggingStatusResponse clustersLoggingStatusGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterIdentifier") final String clusterIdentifier
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeLoggingStatusRequest.class, LoggingStatusResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoggingStatusResult result = client.describeLoggingStatus(request.withClusterIdentifier(clusterIdentifier));
            response.setBucketName(result.getBucketName());
            response.setLastFailureMessage(result.getLastFailureMessage());
            response.setLastFailureTime(result.getLastFailureTime());
            response.setLastSuccessfulDeliveryTime(result.getLastSuccessfulDeliveryTime());
            response.setLoggingEnabled(result.getLoggingEnabled());
            response.setS3KeyPrefix(result.getS3KeyPrefix());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.list",
            path = "{region}/snapshots"
    )
    public SnapshotsResponse snapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClusterSnapshotsRequest.class, SnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterSnapshotsResult result = client.describeClusterSnapshots(request.withMarker(page));
            response.setSnapshots(result.getSnapshots());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedNodes.list",
            path = "{region}/reserved-nodes"
    )
    public ReservedNodesResponse reservedNodesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeReservedNodesRequest.class, ReservedNodesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReservedNodesResult result = client.describeReservedNodes(request.withMarker(page));
            response.setReservedNodes(result.getReservedNodes());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse eventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(request.withMarker(page));
            response.setEvents(result.getEvents());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "eventSubscriptions.list",
            path = "{region}/event-subscriptions"
    )
    public EventSubscriptionsResponse eventSubscriptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeEventSubscriptionsRequest.class, EventSubscriptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventSubscriptionsResult result = client.describeEventSubscriptions(request.withMarker(page));
            response.setSubscriptions(result.getEventSubscriptionsList());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.get",
            path = "{region}/ARN/tags"
    )
    public TagsResponse tagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(request.withResourceName(arn).withMarker(page));
            response.setTags(result.getTaggedResources());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/ARN/tags"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(CreateTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.createTags(r.withResourceName(arn).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "tags.delete",
            path = "{region}/ARN/tags"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DeleteTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.deleteTags(r.withResourceName(arn).withTagKeys(tagKeys));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parameterGroups.list",
            path = "{region}/parameter-groups"
    )
    public ClusterParameterGroupsResponse parameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClusterParameterGroupsRequest.class, ClusterParameterGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterParameterGroupsResult result = client.describeClusterParameterGroups(request.withMarker(page));
            response.setParameterGroups(result.getParameterGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parameterGroups.parameters.list",
            path = "{region}/parameter-groups/{parameterGroupName}/parameters"
    )
    public ClusterParametersResponse parameterGroupsParametersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("parameterGroupName") final String parameterGroupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClusterParametersRequest.class, ClusterParametersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterParametersResult result = client.describeClusterParameters(request.withParameterGroupName(parameterGroupName).withMarker(page));
            response.setParameters(result.getParameters());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public SecurityGroupResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClusterSecurityGroupsRequest.class, SecurityGroupResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterSecurityGroupsResult result = client.describeClusterSecurityGroups(request.withMarker(page));
            response.setSecurityGroups(result.getClusterSecurityGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subnetGroups.list",
            path = "{region}/subnet-groups"
    )
    public SubnetGroupsResponse subnetGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return RedshiftCaller.get(DescribeClusterSubnetGroupsRequest.class, SubnetGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterSubnetGroupsResult result = client.describeClusterSubnetGroups(request.withMarker(page));
            response.setGroups(result.getClusterSubnetGroups());
            response.setNextPage(result.getMarker());
        });
    }
}
