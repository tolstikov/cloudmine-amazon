package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.AWSDirectoryService;
import com.amazonaws.services.directory.model.DescribeConditionalForwardersRequest;
import com.amazonaws.services.directory.model.DescribeConditionalForwardersResult;
import com.amazonaws.services.directory.model.DescribeDirectoriesRequest;
import com.amazonaws.services.directory.model.DescribeDirectoriesResult;
import com.amazonaws.services.directory.model.DescribeEventTopicsRequest;
import com.amazonaws.services.directory.model.DescribeEventTopicsResult;
import com.amazonaws.services.directory.model.DescribeSnapshotsRequest;
import com.amazonaws.services.directory.model.DescribeSnapshotsResult;
import com.amazonaws.services.directory.model.DescribeTrustsRequest;
import com.amazonaws.services.directory.model.DescribeTrustsResult;
import com.amazonaws.services.directory.model.GetDirectoryLimitsRequest;
import com.amazonaws.services.directory.model.GetDirectoryLimitsResult;
import com.amazonaws.services.directory.model.GetSnapshotLimitsRequest;
import com.amazonaws.services.directory.model.GetSnapshotLimitsResult;
import com.amazonaws.services.directory.model.ListIpRoutesRequest;
import com.amazonaws.services.directory.model.ListIpRoutesResult;
import com.amazonaws.services.directory.model.ListSchemaExtensionsRequest;
import com.amazonaws.services.directory.model.ListSchemaExtensionsResult;
import com.amazonaws.services.directory.model.ListTagsForResourceRequest;
import com.amazonaws.services.directory.model.ListTagsForResourceResult;
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

import java.util.List;

@Api(
        name = "directoryservice",
        canonicalName = "DirectoryService",
        title = "AWS Directory Service",
        description = "Managed Microsoft Active Directory in the AWS Cloud",
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

public final class DirectoryServiceApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.list",
            path = "{region}/directories"
    )
    public DirectoriesResponse directoriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") @Nullable final List<String> directoryIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final DescribeDirectoriesResult result = clientWrapper.getClient()
                    .describeDirectories(
                            new DescribeDirectoriesRequest()
                                    .withDirectoryIds(directoryIds)
                                    .withNextToken(page)
                    );
            return new DirectoriesResponse(result.getDirectoryDescriptions(), result.getNextToken());
        } catch (Throwable t) {
            return new DirectoriesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.conditionalForwarders.list",
            path = "{region}/directories/{directoryId}/conditional-forwarders"
    )
    public ConditionalForwardersResponse directoriesConditionalForwardersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("remoteDomainName") @Nullable final List<String> remoteDomainNames
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final DescribeConditionalForwardersResult result = clientWrapper.getClient()
                    .describeConditionalForwarders(
                            new DescribeConditionalForwardersRequest()
                                    .withDirectoryId(directoryId)
                                    .withRemoteDomainNames(remoteDomainNames)
                    );
            return new ConditionalForwardersResponse(result.getConditionalForwarders());
        } catch (Throwable t) {
            return new ConditionalForwardersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.eventTopics.list",
            path = "{region}/directories/{directoryId}/event-topics"
    )
    public EventTopicsResponse directoriesEventTopicsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("topicName") @Nullable final List<String> topicNames
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final DescribeEventTopicsResult result = clientWrapper.getClient()
                    .describeEventTopics(
                            new DescribeEventTopicsRequest()
                                    .withDirectoryId(directoryId)
                                    .withTopicNames(topicNames)
                    );
            return new EventTopicsResponse(result.getEventTopics());
        } catch (Throwable t) {
            return new EventTopicsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.snapshots.list",
            path = "{region}/directories/{directoryId}/snapshots"
    )
    public SnapshotsResponse directoriesSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("snapshotId") @Nullable final List<String> snapshotIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final DescribeSnapshotsResult result = clientWrapper.getClient()
                    .describeSnapshots(
                            new DescribeSnapshotsRequest()
                                    .withDirectoryId(directoryId)
                                    .withSnapshotIds(snapshotIds)
                                    .withNextToken(page)
                    );
            return new SnapshotsResponse(result.getSnapshots(), result.getNextToken());
        } catch (Throwable t) {
            return new SnapshotsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.trusts.list",
            path = "{region}/directories/{directoryId}/trusts"
    )
    public TrustsResponse directoriesTrustsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("trustId") @Nullable final List<String> trustIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final DescribeTrustsResult result = clientWrapper.getClient()
                    .describeTrusts(
                            new DescribeTrustsRequest()
                                    .withDirectoryId(directoryId)
                                    .withTrustIds(trustIds)
                                    .withNextToken(page)
                    );
            return new TrustsResponse(result.getTrusts(), result.getNextToken());
        } catch (Throwable t) {
            return new TrustsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directoryLimits.get",
            path = "{region}/directory-limits"
    )
    public DirectoryLimitsResponse directoryLimitsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final GetDirectoryLimitsResult result = clientWrapper.getClient()
                    .getDirectoryLimits(
                            new GetDirectoryLimitsRequest()
                    );
            return new DirectoryLimitsResponse(result.getDirectoryLimits());
        } catch (Throwable t) {
            return new DirectoryLimitsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.snapshotLimits.get",
            path = "{region}/directories/{directoryId}/snapshot-limits"
    )
    public SnapshotLimitsResponse directoriesSnapshotLimitsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final GetSnapshotLimitsResult result = clientWrapper.getClient()
                    .getSnapshotLimits(
                            new GetSnapshotLimitsRequest()
                                    .withDirectoryId(directoryId)
                    );
            return new SnapshotLimitsResponse(result.getSnapshotLimits());
        } catch (Throwable t) {
            return new SnapshotLimitsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.ipRoutes.list",
            path = "{region}/directories/{directoryId}/ip-routes"
    )
    public IpRoutesResponse directoriesIpRoutesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final ListIpRoutesResult result = clientWrapper.getClient()
                    .listIpRoutes(
                            new ListIpRoutesRequest()
                                    .withDirectoryId(directoryId)
                                    .withNextToken(page)
                    );
            return new IpRoutesResponse(result.getIpRoutesInfo(), result.getNextToken());
        } catch (Throwable t) {
            return new IpRoutesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.schemaExtensions.list",
            path = "{region}/directories/{directoryId}/schema-extensions"
    )
    public SchemaExtensionsResponse directoriesSchemaExtensionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final ListSchemaExtensionsResult result = clientWrapper.getClient()
                    .listSchemaExtensions(
                            new ListSchemaExtensionsRequest()
                                    .withDirectoryId(directoryId)
                                    .withNextToken(page)
                    );
            return new SchemaExtensionsResponse(result.getSchemaExtensionsInfo(), result.getNextToken());
        } catch (Throwable t) {
            return new SchemaExtensionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directories.tags.get",
            path = "{region}/directories/{directoryId}/tags"
    )
    public TagsResponse directoriesTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("directoryId") final String directoryId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSDirectoryService> clientWrapper = new AmazonClientHelper(credentials).getDirectoryService(region)) {
            final ListTagsForResourceResult result = clientWrapper.getClient()
                    .listTagsForResource(
                            new ListTagsForResourceRequest()
                                    .withResourceId(directoryId)
                                    .withNextToken(page)
                    );
            return new TagsResponse(result.getTags(), result.getNextToken());
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t));
        }
    }
}
