package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.CreateTagsRequest;
import com.amazonaws.services.workspaces.model.DeleteTagsRequest;
import com.amazonaws.services.workspaces.model.DescribeIpGroupsRequest;
import com.amazonaws.services.workspaces.model.DescribeIpGroupsResult;
import com.amazonaws.services.workspaces.model.DescribeTagsRequest;
import com.amazonaws.services.workspaces.model.DescribeTagsResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesConnectionStatusRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesConnectionStatusResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesResult;
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

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "workspaces",
        canonicalName = "Workspaces",
        title = "Amazon WorkSpaces",
        description = "Desktop Computing Service",
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
public final class WorkspacesApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaces.list",
            path = "{region}/workspaces"
    )
    public WorkspacesResponse workspacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspacesRequest.class, WorkspacesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspacesResult result = client.describeWorkspaces(request.withNextToken(page));
            response.setWorkspaces(result.getWorkspaces());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaces.connectionStatuses.list",
            path = "{region}/workspaces/connection-statuses"
    )
    public ConnectionStatusesResponse workspacesConnectionStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("workspaceId") @Nullable final List<String> workspaceIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspacesConnectionStatusRequest.class, ConnectionStatusesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspacesConnectionStatusResult result = client.describeWorkspacesConnectionStatus(
                    request
                            .withWorkspaceIds(workspaceIds)
                            .withNextToken(page)
            );
            response.setConnectionStatuses(result.getWorkspacesConnectionStatus());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaces.tags.list",
            path = "{region}/workspaces/{workspaceId}/tags"
    )
    public TagsResponse workspacesTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("workspaceId") final String workspaceId
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(request.withResourceId(workspaceId));
            response.setTags(result.getTagList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "workspaces.tags.create",
            path = "{region}/workspaces/{workspaceId}/tags"
    )
    public AmazonResponse fileSystemagsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("workspaceId") final String workspaceId,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(CreateTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.createTags(r.withResourceId(workspaceId).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "workspaces.tags.delete",
            path = "{region}/workspaces/{workspaceId}/tags"
    )
    public AmazonResponse fileSystemTagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("workspaceId") final String workspaceId,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DeleteTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.deleteTags(r.withResourceId(workspaceId).withTagKeys(tagKeys));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaceDirectories.list",
            path = "{region}/workspace-directories"
    )
    public WorkspaceDirectoriesResponse workspaceDirectoriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspaceDirectoriesRequest.class, WorkspaceDirectoriesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspaceDirectoriesResult result = client.describeWorkspaceDirectories(request.withNextToken(page));
            response.setWorkspaceDirectories(result.getDirectories());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "workspaceBundles.list",
            path = "{region}/workspace-bundles"
    )
    public WorkspaceBundlesResponse workspaceBundlesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeWorkspaceBundlesRequest.class, WorkspaceBundlesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeWorkspaceBundlesResult result = client.describeWorkspaceBundles(request.withNextToken(page));
            response.setWorkspaceBundles(result.getBundles());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "ipGroups.list",
            path = "{region}/ip-groups"
    )
    public IpGroupsResponse ipGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupId") @Nullable final List<String> groupIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WorkspacesCaller.get(DescribeIpGroupsRequest.class, IpGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeIpGroupsResult result = client.describeIpGroups(
                    request
                            .withGroupIds(groupIds)
                            .withNextToken(page)
            );
            response.setIpGroups(result.getResult());
            response.setNextPage(result.getNextToken());
        });
    }
}
