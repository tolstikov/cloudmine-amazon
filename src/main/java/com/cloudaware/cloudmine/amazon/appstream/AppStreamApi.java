package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.DescribeDirectoryConfigsRequest;
import com.amazonaws.services.appstream.model.DescribeDirectoryConfigsResult;
import com.amazonaws.services.appstream.model.DescribeFleetsRequest;
import com.amazonaws.services.appstream.model.DescribeFleetsResult;
import com.amazonaws.services.appstream.model.DescribeImageBuildersRequest;
import com.amazonaws.services.appstream.model.DescribeImageBuildersResult;
import com.amazonaws.services.appstream.model.DescribeImagePermissionsRequest;
import com.amazonaws.services.appstream.model.DescribeImagePermissionsResult;
import com.amazonaws.services.appstream.model.DescribeImagesRequest;
import com.amazonaws.services.appstream.model.DescribeImagesResult;
import com.amazonaws.services.appstream.model.DescribeSessionsRequest;
import com.amazonaws.services.appstream.model.DescribeSessionsResult;
import com.amazonaws.services.appstream.model.DescribeStacksRequest;
import com.amazonaws.services.appstream.model.DescribeStacksResult;
import com.amazonaws.services.appstream.model.ListAssociatedFleetsRequest;
import com.amazonaws.services.appstream.model.ListAssociatedFleetsResult;
import com.amazonaws.services.appstream.model.ListTagsForResourceRequest;
import com.amazonaws.services.appstream.model.ListTagsForResourceResult;
import com.amazonaws.services.appstream.model.TagResourceRequest;
import com.amazonaws.services.appstream.model.TagResourceResult;
import com.amazonaws.services.appstream.model.UntagResourceRequest;
import com.amazonaws.services.appstream.model.UntagResourceResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Description;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "appstream",
        canonicalName = "AppStream",
        title = "Amazon AppStream",
        description = "You can use Amazon AppStream 2.0 to stream desktop applications to any device running a web browser, without rewriting them",
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
public final class AppStreamApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.list",
            path = "{region}/stacks"
    )
    public StacksResponse stacksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeStacksRequest.class, StacksResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStacksResult result = client.describeStacks(
                    request.withNextToken(page)
            );
            response.setStacks(result.getStacks());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.fleets.list",
            path = "{region}/stacks/{stackName}/fleets"
    )
    public AssociatedFleetsResponse stacksFleetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(ListAssociatedFleetsRequest.class, AssociatedFleetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListAssociatedFleetsResult result = client.listAssociatedFleets(
                    request.withStackName(stackName).withNextToken(page)
            );
            response.setFleetNames(result.getNames());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "directoryConfigs.list",
            path = "{region}/directory-configs"
    )
    public DirectoryConfigsResponse directoryConfigsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeDirectoryConfigsRequest.class, DirectoryConfigsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDirectoryConfigsResult result = client.describeDirectoryConfigs(
                    request.withNextToken(page)
            );
            response.setDirectoryConfigs(result.getDirectoryConfigs());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "fleets.list",
            path = "{region}/fleets"
    )
    public FleetsResponse fleetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeFleetsRequest.class, FleetsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeFleetsResult result = client.describeFleets(
                    request.withNextToken(page)
            );
            response.setFleets(result.getFleets());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "fleets.sessions.list",
            path = "{region}/fleets/{fleetName}/sessions"
    )
    public SessionsResponse fleetsSessionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("fleetName") final String fleetName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeSessionsRequest.class, SessionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSessionsResult result = client.describeSessions(
                    request.withFleetName(fleetName).withNextToken(page)
            );
            response.setSessions(result.getSessions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "imageBuilders.list",
            path = "{region}/image-builders"
    )
    public ImageBuildersResponse imageBuildersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeImageBuildersRequest.class, ImageBuildersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImageBuildersResult result = client.describeImageBuilders(
                    request.withNextToken(page)
            );
            response.setImageBuilders(result.getImageBuilders());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.list",
            path = "{region}/images"
    )
    public ImagesResponse imagesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("type") @Description("Valid Values: PUBLIC | PRIVATE | SHARED") @Nullable final String type,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeImagesRequest.class, ImagesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImagesResult result = client.describeImages(
                    request
                            .withType(type)
                            .withNextToken(page)
            );
            response.setImages(result.getImages());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.permissions.list",
            path = "{region}/images/{imageName}/permissions"
    )
    public SharedImagePermissionsResponse imagesPermissionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("imageName") final String imageName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(DescribeImagePermissionsRequest.class, SharedImagePermissionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImagePermissionsResult result = client.describeImagePermissions(
                    request
                            .withName(imageName)
                            .withNextToken(page)
            );
            response.setSharedImagePermissions(result.getSharedImagePermissionsList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags/{resourceArn}"
    )
    public TagsResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") @Description("You can tag AppStream 2.0 image builders, images, fleets, and stacks") final String resourceArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(
                    request.withResourceArn(resourceArn)
            );
            response.setTags(result.getTags());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "resources.tags.list",
            path = "{region}/resources/{resourceArn}/tags"
    )
    public TagsResponse resourcesTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") @Description("You can list tags of AppStream 2.0 image builders, images, fleets, and stacks") final String resourceArn
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(
                    request.withResourceArn(resourceArn)
            );
            response.setTags(result.getTags());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.tag",
            path = "{region}/resources/{resourceArn}/tags"
    )
    public AmazonResponse resourcesTagsTag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") @Description("You can tag AppStream 2.0 image builders, images, fleets, and stacks") final String resourceArn,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            final TagResourceResult result = client.tagResource(
                    request.withResourceArn(resourceArn).withTags(tagsRequest.getTags())
            );
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "resources.tags.untag",
            path = "{region}/resources/{resourceArn}/tags"
    )
    public AmazonResponse resourcesTagsUntag(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") @Description("You can untag AppStream 2.0 image builders, images, fleets, and stacks") final String resourceArn,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return AppStreamCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            final UntagResourceResult result = client.untagResource(
                    request.withResourceArn(resourceArn).withTagKeys(tagsRequest.getTags().keySet())
            );
        });
    }

}
