package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.model.AddTagsToResourceRequest;
import com.amazonaws.services.rds.model.DBClusterSnapshotAttribute;
import com.amazonaws.services.rds.model.DBSnapshotAttribute;
import com.amazonaws.services.rds.model.DescribeAccountAttributesResult;
import com.amazonaws.services.rds.model.DescribeDBClusterParameterGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterParameterGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotAttributesRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotsRequest;
import com.amazonaws.services.rds.model.DescribeDBClusterSnapshotsResult;
import com.amazonaws.services.rds.model.DescribeDBClustersRequest;
import com.amazonaws.services.rds.model.DescribeDBClustersResult;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.amazonaws.services.rds.model.DescribeDBParameterGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBParameterGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBSecurityGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBSecurityGroupsResult;
import com.amazonaws.services.rds.model.DescribeDBSnapshotAttributesRequest;
import com.amazonaws.services.rds.model.DescribeDBSnapshotsRequest;
import com.amazonaws.services.rds.model.DescribeDBSnapshotsResult;
import com.amazonaws.services.rds.model.DescribeDBSubnetGroupsRequest;
import com.amazonaws.services.rds.model.DescribeDBSubnetGroupsResult;
import com.amazonaws.services.rds.model.DescribeEventsRequest;
import com.amazonaws.services.rds.model.DescribeEventsResult;
import com.amazonaws.services.rds.model.DescribeReservedDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeReservedDBInstancesResult;
import com.amazonaws.services.rds.model.ListTagsForResourceRequest;
import com.amazonaws.services.rds.model.RemoveTagsFromResourceRequest;
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
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "rds",
        canonicalName = "Rds",
        title = "Amazon RDS",
        description = "Managed Relational Database Service for MySQL, PostgreSQL, Oracle, SQL Server, and MariaDB",
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
public final class RdsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbInstances.list",
            path = "{region}/db-instances"
    )
    public DbInstancesResponse dbInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBInstancesResult result = clientWrapper.getClient().describeDBInstances(
                    new DescribeDBInstancesRequest()
                            .withMarker(page)
            );
            return new DbInstancesResponse(result.getDBInstances(), result.getMarker());
        } catch (Throwable t) {
            return new DbInstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbParameterGroups.list",
            path = "{region}/db-parameter-groups"
    )
    public DbParameterGroupsResponse dbParameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBParameterGroupsResult result = clientWrapper.getClient().describeDBParameterGroups(
                    new DescribeDBParameterGroupsRequest()
                            .withMarker(page)
            );
            return new DbParameterGroupsResponse(result.getDBParameterGroups(), result.getMarker());
        } catch (Throwable t) {
            return new DbParameterGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSecurityGroups.list",
            path = "{region}/db-security-groups"
    )
    public DbSecurityGroupsResponse dbSecurityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBSecurityGroupsResult result = clientWrapper.getClient().describeDBSecurityGroups(
                    new DescribeDBSecurityGroupsRequest()
                            .withMarker(page)
            );
            return new DbSecurityGroupsResponse(result.getDBSecurityGroups(), result.getMarker());
        } catch (Throwable t) {
            return new DbSecurityGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSubnetGroups.list",
            path = "{region}/db-subnet-groups"
    )
    public DbSubnetGroupsResponse dbSubnetGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBSubnetGroupsResult result = clientWrapper.getClient().describeDBSubnetGroups(
                    new DescribeDBSubnetGroupsRequest()
                            .withMarker(page)
            );
            return new DbSubnetGroupsResponse(result.getDBSubnetGroups(), result.getMarker());
        } catch (Throwable t) {
            return new DbSubnetGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSnapshots.list",
            path = "{region}/db-snapshots"
    )
    public DbSnapshotsResponse dbSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBSnapshotsResult result = clientWrapper.getClient().describeDBSnapshots(
                    new DescribeDBSnapshotsRequest()
                            .withMarker(page)
            );
            return new DbSnapshotsResponse(result.getDBSnapshots(), result.getMarker());
        } catch (Throwable t) {
            return new DbSnapshotsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbSnapshots.attributes.list",
            path = "{region}/dbSnapshots/{dbSnapshotId}/attributes"
    )
    public DbSnapshotAttributesResponse dbSnapshotsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("dbSnapshotId") final String dbSnapshotId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final List<DBSnapshotAttribute> dbSnapshotAttributes = clientWrapper.getClient().describeDBSnapshotAttributes(
                    new DescribeDBSnapshotAttributesRequest()
                            .withDBSnapshotIdentifier(dbSnapshotId)
            ).getDBSnapshotAttributes();
            return new DbSnapshotAttributesResponse(dbSnapshotAttributes);
        } catch (Throwable t) {
            return new DbSnapshotAttributesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusters.list",
            path = "{region}/db-clusters"
    )
    public DbClustersResponse dbClustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBClustersResult result = clientWrapper.getClient().describeDBClusters(
                    new DescribeDBClustersRequest()
                            .withMarker(page)
            );
            return new DbClustersResponse(result.getDBClusters(), result.getMarker());
        } catch (Throwable t) {
            return new DbClustersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterSnapshots.list",
            path = "{region}/db-cluster-snapshots"
    )
    public DbClusterSnapshotsResponse dbClusterSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBClusterSnapshotsResult result = clientWrapper.getClient().describeDBClusterSnapshots(
                    new DescribeDBClusterSnapshotsRequest()
                            .withMarker(page)
            );
            return new DbClusterSnapshotsResponse(result.getDBClusterSnapshots(), result.getMarker());
        } catch (Throwable t) {
            return new DbClusterSnapshotsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterSnapshots.attributes.list",
            path = "{region}/dbClusterSnapshots/{dbClusterSnapshotId}/attributes"
    )
    public DbClusterSnapshotAttributesResponse dbClusterSnapshotsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("dbClusterSnapshotId") final String dbClusterSnapshotId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final List<DBClusterSnapshotAttribute> dbClusterSnapshotAttributes = clientWrapper.getClient().describeDBClusterSnapshotAttributes(
                    new DescribeDBClusterSnapshotAttributesRequest()
                            .withDBClusterSnapshotIdentifier(dbClusterSnapshotId)
            ).getDBClusterSnapshotAttributes();
            return new DbClusterSnapshotAttributesResponse(dbClusterSnapshotAttributes);
        } catch (Throwable t) {
            return new DbClusterSnapshotAttributesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dbClusterParameterGroups.list",
            path = "{region}/db-cluster-parameter-groups"
    )
    public DbClusterParameterGroupsResponse dbClusterParameterGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeDBClusterParameterGroupsResult result = clientWrapper.getClient().describeDBClusterParameterGroups(
                    new DescribeDBClusterParameterGroupsRequest()
                            .withMarker(page)
            );
            return new DbClusterParameterGroupsResponse(result.getDBClusterParameterGroups(), result.getMarker());
        } catch (Throwable t) {
            return new DbClusterParameterGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedDbInstances.list",
            path = "{region}/reserved-db-instances"
    )
    public ReservedDbInstancesResponse reservedDbInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeReservedDBInstancesResult result = clientWrapper.getClient().describeReservedDBInstances(
                    new DescribeReservedDBInstancesRequest()
                            .withMarker(page)
            );
            return new ReservedDbInstancesResponse(result.getReservedDBInstances(), result.getMarker());
        } catch (Throwable t) {
            return new ReservedDbInstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse dbEventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("durationInMinutes") @Nullable final Integer durationInMinutes,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeEventsResult result = clientWrapper.getClient().describeEvents(
                    new DescribeEventsRequest()
                            .withDuration(durationInMinutes)
                            .withMarker(page)
            );
            return new EventsResponse(result.getEvents(), result.getMarker());
        } catch (Throwable t) {
            return new EventsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountAttributes.list",
            path = "{region}/account-attributes"
    )
    public AccountQuotasResponse accountAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final DescribeAccountAttributesResult result = clientWrapper.getClient().describeAccountAttributes();
            return new AccountQuotasResponse(result.getAccountQuotas());
        } catch (Throwable t) {
            return new AccountQuotasResponse(AmazonResponse.parse(t));
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
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            return new TagsResponse(clientWrapper.getClient().listTagsForResource(new ListTagsForResourceRequest().withResourceName(arn)).getTagList());
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse tagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final List<com.amazonaws.services.rds.model.Tag> tags = Lists.newArrayList();
            for (final Map.Entry<String, String> e : tagsRequest.getTags().entrySet()) {
                tags.add(new com.amazonaws.services.rds.model.Tag().withKey(e.getKey()).withValue(e.getValue()));
            }
            final AddTagsToResourceRequest req = new AddTagsToResourceRequest()
                    .withTags(tags)
                    .withResourceName(tagsRequest.getResourceArn());
            clientWrapper.getClient().addTagsToResource(req);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final RemoveTagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonRDS> clientWrapper = new AmazonClientHelper(credentials).getRds(region)) {
            final RemoveTagsFromResourceRequest req = new RemoveTagsFromResourceRequest()
                    .withTagKeys(tagsRequest.getTagKeys())
                    .withResourceName(tagsRequest.getResourceArn());
            clientWrapper.getClient().removeTagsFromResource(req);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }
}
