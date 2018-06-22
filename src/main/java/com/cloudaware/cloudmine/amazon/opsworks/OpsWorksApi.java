package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.DescribeAppsRequest;
import com.amazonaws.services.opsworks.model.DescribeAppsResult;
import com.amazonaws.services.opsworks.model.DescribeElasticIpsRequest;
import com.amazonaws.services.opsworks.model.DescribeElasticIpsResult;
import com.amazonaws.services.opsworks.model.DescribeInstancesRequest;
import com.amazonaws.services.opsworks.model.DescribeInstancesResult;
import com.amazonaws.services.opsworks.model.DescribeLayersRequest;
import com.amazonaws.services.opsworks.model.DescribeLayersResult;
import com.amazonaws.services.opsworks.model.DescribePermissionsRequest;
import com.amazonaws.services.opsworks.model.DescribePermissionsResult;
import com.amazonaws.services.opsworks.model.DescribeRdsDbInstancesRequest;
import com.amazonaws.services.opsworks.model.DescribeRdsDbInstancesResult;
import com.amazonaws.services.opsworks.model.DescribeStackSummaryRequest;
import com.amazonaws.services.opsworks.model.DescribeStackSummaryResult;
import com.amazonaws.services.opsworks.model.DescribeStacksRequest;
import com.amazonaws.services.opsworks.model.DescribeStacksResult;
import com.amazonaws.services.opsworks.model.DescribeUserProfilesRequest;
import com.amazonaws.services.opsworks.model.DescribeUserProfilesResult;
import com.amazonaws.services.opsworks.model.DescribeVolumesRequest;
import com.amazonaws.services.opsworks.model.DescribeVolumesResult;
import com.amazonaws.services.opsworks.model.ListTagsRequest;
import com.amazonaws.services.opsworks.model.ListTagsResult;
import com.amazonaws.services.opsworks.model.TagResourceRequest;
import com.amazonaws.services.opsworks.model.UntagResourceRequest;
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
        name = "opsworks",
        canonicalName = "OpsWorks",
        title = "AWS OpsWorks",
        description = "Automate Operations with Chef and Puppet",
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
public final class OpsWorksApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.list",
            path = "{region}/stacks"
    )
    public StacksResponse stacksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") @Nullable final List<String> stackIds
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeStacksRequest.class, StacksResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStacksResult result = client.describeStacks(request.withStackIds(stackIds));
            response.setStacks(result.getStacks());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.stackDetails.get",
            path = "{region}/stacks/{stackId}/stack-details"
    )
    public StackDetailsResponse stacksStackDetailsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeStackSummaryRequest.class, StackDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStackSummaryResult result = client.describeStackSummary(request.withStackId(stackId));
            response.setStackDetails(result.getStackSummary());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.layers.list",
            path = "{region}/stacks/{stackId}/layers"
    )
    public LayersResponse stacksLayersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeLayersRequest.class, LayersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLayersResult result = client.describeLayers(request.withStackId(stackId));
            response.setLayers(result.getLayers());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.instances.list",
            path = "{region}/stacks/{stackId}/instances"
    )
    public InstancesResponse stacksInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeInstancesRequest.class, InstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInstancesResult result = client.describeInstances(request.withStackId(stackId));
            response.setInstances(result.getInstances());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.apps.list",
            path = "{region}/stacks/{stackId}/apps"
    )
    public AppsResponse stacksAppsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeAppsRequest.class, AppsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAppsResult result = client.describeApps(request.withStackId(stackId));
            response.setApps(result.getApps());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.elasticIps.list",
            path = "{region}/stacks/{stackId}/elastic-ips"
    )
    public ElasticIpsResponse stacksElasticIpsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeElasticIpsRequest.class, ElasticIpsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeElasticIpsResult result = client.describeElasticIps(request.withStackId(stackId));
            response.setElasticIps(result.getElasticIps());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.rdsDbInstances.list",
            path = "{region}/stacks/{stackId}/rds-db-instances"
    )
    public RdsDbInstancesResponse stacksRdsDbInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeRdsDbInstancesRequest.class, RdsDbInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRdsDbInstancesResult result = client.describeRdsDbInstances(request.withStackId(stackId));
            response.setRdsDbInstances(result.getRdsDbInstances());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.volumes.list",
            path = "{region}/stacks/{stackId}/volumes"
    )
    public VolumesResponse stacksVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeVolumesRequest.class, VolumesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVolumesResult result = client.describeVolumes(request.withStackId(stackId));
            response.setVolumes(result.getVolumes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.permissions.list",
            path = "{region}/stacks/{stackId}/permissions"
    )
    public PermissionsResponse stacksPermissionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackId") final String stackId
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribePermissionsRequest.class, PermissionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribePermissionsResult result = client.describePermissions(request.withStackId(stackId));
            response.setPermissions(result.getPermissions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "userProfiles.list",
            path = "{region}/user-profiles"
    )
    public UserProfilesResponse userProfilesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("iamUserArn") @Nullable final List<String> iamUserArns
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(DescribeUserProfilesRequest.class, UserProfilesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeUserProfilesResult result = client.describeUserProfiles(request.withIamUserArns(iamUserArns));
            response.setUserProfiles(result.getUserProfiles());
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
            @Named("resourceArn") final String resourceArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(ListTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsResult result = client.listTags(
                    request
                            .withResourceArn(resourceArn)
                            .withNextToken(page)
            );
            response.setTags(result.getTags());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.add",
            path = "{region}/resources/{resourceArn}/tags"
    )
    public AmazonResponse resourcesTagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            final AddTagsRequest request
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.tagResource(r.withResourceArn(resourceArn).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "resources.tags.remove",
            path = "{region}/resources/{resourceArn}/tags"
    )
    public AmazonResponse resourcesTagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return OpsWorksCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.untagResource(r.withResourceArn(resourceArn).withTagKeys(tagKeys));
        });
    }
}
