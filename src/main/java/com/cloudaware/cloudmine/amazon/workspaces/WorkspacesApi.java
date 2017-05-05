package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.AmazonWorkspaces;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceBundlesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspaceDirectoriesResult;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesRequest;
import com.amazonaws.services.workspaces.model.DescribeWorkspacesResult;
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
        try (ClientWrapper<AmazonWorkspaces> clientWrapper = new AmazonClientHelper(credentials).getWorkspaces(region)) {
            final DescribeWorkspacesResult result = clientWrapper.getClient().describeWorkspaces(
                    new DescribeWorkspacesRequest()
                            .withNextToken(page)
            );
            return new WorkspacesResponse(result.getWorkspaces(), result.getNextToken());
        } catch (Throwable t) {
            return new WorkspacesResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonWorkspaces> clientWrapper = new AmazonClientHelper(credentials).getWorkspaces(region)) {
            final DescribeWorkspaceDirectoriesResult result = clientWrapper.getClient().describeWorkspaceDirectories(
                    new DescribeWorkspaceDirectoriesRequest()
                            .withNextToken(page)
            );
            return new WorkspaceDirectoriesResponse(result.getDirectories(), result.getNextToken());
        } catch (Throwable t) {
            return new WorkspaceDirectoriesResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonWorkspaces> clientWrapper = new AmazonClientHelper(credentials).getWorkspaces(region)) {
            final DescribeWorkspaceBundlesResult result = clientWrapper.getClient().describeWorkspaceBundles(
                    new DescribeWorkspaceBundlesRequest()
                            .withNextToken(page)
            );
            return new WorkspaceBundlesResponse(result.getBundles(), result.getNextToken());
        } catch (Throwable t) {
            return new WorkspaceBundlesResponse(AmazonResponse.parse(t));
        }
    }

}
