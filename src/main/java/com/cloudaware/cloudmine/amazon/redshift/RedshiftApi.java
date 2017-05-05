package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.model.CreateTagsRequest;
import com.amazonaws.services.redshift.model.DeleteTagsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsRequest;
import com.amazonaws.services.redshift.model.DescribeClusterSnapshotsResult;
import com.amazonaws.services.redshift.model.DescribeClustersRequest;
import com.amazonaws.services.redshift.model.DescribeClustersResult;
import com.amazonaws.services.redshift.model.DescribeEventsRequest;
import com.amazonaws.services.redshift.model.DescribeEventsResult;
import com.amazonaws.services.redshift.model.DescribeReservedNodesRequest;
import com.amazonaws.services.redshift.model.DescribeReservedNodesResult;
import com.amazonaws.services.redshift.model.DescribeTagsRequest;
import com.amazonaws.services.redshift.model.DescribeTagsResult;
import com.amazonaws.services.redshift.model.Tag;
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
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
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
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DescribeClustersResult result = clientWrapper.getClient().describeClusters(
                    new DescribeClustersRequest()
                            .withMarker(page)
            );
            return new ClustersResponse(result.getClusters(), result.getMarker());
        } catch (Throwable t) {
            return new ClustersResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DescribeClusterSnapshotsResult result = clientWrapper.getClient().describeClusterSnapshots(
                    new DescribeClusterSnapshotsRequest()
                            .withMarker(page)
            );
            return new SnapshotsResponse(result.getSnapshots(), result.getMarker());
        } catch (Throwable t) {
            return new SnapshotsResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DescribeReservedNodesResult result = clientWrapper.getClient().describeReservedNodes(
                    new DescribeReservedNodesRequest()
                            .withMarker(page)
            );
            return new ReservedNodesResponse(result.getReservedNodes(), result.getMarker());
        } catch (Throwable t) {
            return new ReservedNodesResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DescribeEventsResult result = clientWrapper.getClient().describeEvents(
                    new DescribeEventsRequest()
                            .withMarker(page)
            );
            return new EventsResponse(result.getEvents(), result.getMarker());
        } catch (Throwable t) {
            return new EventsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.get",
            path = "{region}/tags/ARN"
    )
    public TagsResponse tagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DescribeTagsResult result = clientWrapper.getClient().describeTags(
                    new DescribeTagsRequest()
                            .withResourceName(arn)
                            .withMarker(page)
            );
            return new TagsResponse(result.getTaggedResources(), result.getMarker());
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final CreateTagsRequest createTagsRequest = new CreateTagsRequest();
            createTagsRequest.withResourceName(request.getArn());
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            createTagsRequest.withTags(tags);
            clientWrapper.getClient().createTags(createTagsRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRedshift> clientWrapper = new AmazonClientHelper(credentials).getRedshift(region)) {
            final DeleteTagsRequest deleteTagsRequest = new DeleteTagsRequest();
            deleteTagsRequest.withResourceName(request.getArn());
            deleteTagsRequest.withTagKeys(request.getTags().keySet());
            clientWrapper.getClient().deleteTags(deleteTagsRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

}
