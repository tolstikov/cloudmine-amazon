package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.AmazonElastiCache;
import com.amazonaws.services.elasticache.model.AddTagsToResourceRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersResult;
import com.amazonaws.services.elasticache.model.DescribeCacheParameterGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheParameterGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeCacheSecurityGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheSecurityGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeCacheSubnetGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheSubnetGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeEventsRequest;
import com.amazonaws.services.elasticache.model.DescribeEventsResult;
import com.amazonaws.services.elasticache.model.DescribeReplicationGroupsRequest;
import com.amazonaws.services.elasticache.model.DescribeReplicationGroupsResult;
import com.amazonaws.services.elasticache.model.DescribeReservedCacheNodesRequest;
import com.amazonaws.services.elasticache.model.DescribeReservedCacheNodesResult;
import com.amazonaws.services.elasticache.model.DescribeSnapshotsRequest;
import com.amazonaws.services.elasticache.model.DescribeSnapshotsResult;
import com.amazonaws.services.elasticache.model.ListTagsForResourceRequest;
import com.amazonaws.services.elasticache.model.ListTagsForResourceResult;
import com.amazonaws.services.elasticache.model.RemoveTagsFromResourceRequest;
import com.amazonaws.services.elasticache.model.Tag;
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
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "elasticache",
        canonicalName = "ElastiCache",
        title = "Amazon ElastiCache",
        description = "In-Memory Caching Syste",
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
public final class ElastiCacheApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public CacheClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeCacheClustersResult result = clientWrapper.getClient().describeCacheClusters(
                    new DescribeCacheClustersRequest()
                            .withShowCacheNodeInfo(true)
                            .withMarker(page)
            );
            return new CacheClustersResponse(result.getCacheClusters(), result.getMarker());
        } catch (Throwable t) {
            return new CacheClustersResponse(AmazonResponse.parse(t));
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
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeSnapshotsResult result = clientWrapper.getClient().describeSnapshots(
                    new DescribeSnapshotsRequest()
                            .withMarker(page)
            );
            return new SnapshotsResponse(result.getSnapshots(), result.getMarker());
        } catch (Throwable t) {
            return new SnapshotsResponse(AmazonResponse.parse(t));
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
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
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
            name = "parameterGroups.list",
            path = "{region}/parameter-groups"
    )
    public ParameterGroupsResponse parameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeCacheParameterGroupsResult result = clientWrapper.getClient().describeCacheParameterGroups(
                    new DescribeCacheParameterGroupsRequest()
                            .withMarker(page)
            );
            return new ParameterGroupsResponse(result.getCacheParameterGroups(), result.getMarker());
        } catch (Throwable t) {
            return new ParameterGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "replicationGroups.list",
            path = "{region}/replication-groups"
    )
    public ReplicationGroupsResponse replicationGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeReplicationGroupsResult result = clientWrapper.getClient().describeReplicationGroups(
                    new DescribeReplicationGroupsRequest()
                            .withMarker(page)
            );
            return new ReplicationGroupsResponse(result.getReplicationGroups(), result.getMarker());
        } catch (Throwable t) {
            return new ReplicationGroupsResponse(AmazonResponse.parse(t));
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
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeReservedCacheNodesResult result = clientWrapper.getClient().describeReservedCacheNodes(
                    new DescribeReservedCacheNodesRequest()
                            .withMarker(page)
            );
            return new ReservedNodesResponse(result.getReservedCacheNodes(), result.getMarker());
        } catch (Throwable t) {
            return new ReservedNodesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public CacheSecurityGroupsResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeCacheSecurityGroupsResult result = clientWrapper.getClient().describeCacheSecurityGroups(
                    new DescribeCacheSecurityGroupsRequest()
                            .withMarker(page)
            );
            return new CacheSecurityGroupsResponse(result.getCacheSecurityGroups(), result.getMarker());
        } catch (Throwable t) {
            return new CacheSecurityGroupsResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final DescribeCacheSubnetGroupsResult result = clientWrapper.getClient().describeCacheSubnetGroups(
                    new DescribeCacheSubnetGroupsRequest()
                            .withMarker(page)
            );
            return new SubnetGroupsResponse(result.getCacheSubnetGroups(), result.getMarker());
        } catch (Throwable t) {
            return new SubnetGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.get",
            path = "{region}/tags/ARN"
    )
    public TagsResponse tagsForResource(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final ListTagsForResourceResult result = clientWrapper.getClient().listTagsForResource(new ListTagsForResourceRequest().withResourceName(arn));
            final Map<String, String> out = Maps.newHashMap();
            if (result != null && result.getTagList().size() > 0) {
                for (final Tag tag : result.getTagList()) {
                    out.put(tag.getKey(), tag.getValue());
                }
            }
            return new TagsResponse(out);
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse addTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final AddTagsToResourceRequest addTagsToResourceRequest = new AddTagsToResourceRequest();
            addTagsToResourceRequest.withResourceName(request.getArn());
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            addTagsToResourceRequest.withTags(tags);
            clientWrapper.getClient().addTagsToResource(addTagsToResourceRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/delete"
    )
    public AmazonResponse deleteTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElastiCache> clientWrapper = new AmazonClientHelper(credentials).getElastiCache(region)) {
            final RemoveTagsFromResourceRequest removeTagsFromResourceRequest = new RemoveTagsFromResourceRequest();
            removeTagsFromResourceRequest.withResourceName(request.getArn());
            removeTagsFromResourceRequest.withTagKeys(request.getTags().keySet());
            clientWrapper.getClient().removeTagsFromResource(removeTagsFromResourceRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }
}
