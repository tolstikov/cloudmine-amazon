package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.DescribeAccountLimitsRequest;
import com.amazonaws.services.autoscaling.model.DescribeAccountLimitsResult;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsRequest;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsRequest;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsResult;
import com.amazonaws.services.autoscaling.model.DescribeLifecycleHooksRequest;
import com.amazonaws.services.autoscaling.model.DescribeLifecycleHooksResult;
import com.amazonaws.services.autoscaling.model.DescribeNotificationConfigurationsRequest;
import com.amazonaws.services.autoscaling.model.DescribeNotificationConfigurationsResult;
import com.amazonaws.services.autoscaling.model.DescribePoliciesRequest;
import com.amazonaws.services.autoscaling.model.DescribePoliciesResult;
import com.amazonaws.services.autoscaling.model.DescribeScalingActivitiesRequest;
import com.amazonaws.services.autoscaling.model.DescribeScalingActivitiesResult;
import com.amazonaws.services.autoscaling.model.DescribeScheduledActionsRequest;
import com.amazonaws.services.autoscaling.model.DescribeScheduledActionsResult;
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
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "autoscaling",
        canonicalName = "AutoScaling",
        title = "Auto Scaling",
        description = "Automatic Elasticity",
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
public final class AutoScalingApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.list",
            path = "{region}/groups"
    )
    public AutoScalingGroupsResponse groupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") @Nullable final List<String> groupNames,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeAutoScalingGroupsRequest.class, AutoScalingGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAutoScalingGroupsResult result = client.describeAutoScalingGroups(
                    request
                            .withAutoScalingGroupNames(groupNames)
                            .withNextToken(page)
            );
            response.setAutoScalingGroups(result.getAutoScalingGroups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "launchConfigurations.list",
            path = "{region}/launch-configurations"
    )
    public LaunchConfigurationsResponse launchConfigurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeLaunchConfigurationsRequest.class, LaunchConfigurationsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLaunchConfigurationsResult result = client.describeLaunchConfigurations(request.withNextToken(page));
            response.setLaunchConfigurations(result.getLaunchConfigurations());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "{region}/policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") @Nullable final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribePoliciesRequest.class, PoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribePoliciesResult result = client.describePolicies(request.withNextToken(page).withAutoScalingGroupName(groupName));
            response.setPolicies(result.getScalingPolicies());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountLimits.get",
            path = "{region}/account-limits"
    )
    public AccountLimitsResponse accountLimitsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeAccountLimitsRequest.class, AccountLimitsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAccountLimitsResult result = client.describeAccountLimits(request);
            response.setMaxNumberOfAutoScalingGroups(result.getMaxNumberOfAutoScalingGroups());
            response.setNumberOfAutoScalingGroups(result.getNumberOfAutoScalingGroups());
            response.setMaxNumberOfLaunchConfigurations(result.getMaxNumberOfLaunchConfigurations());
            response.setNumberOfLaunchConfigurations(result.getNumberOfLaunchConfigurations());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "lifecycleHooks.list",
            path = "{region}/lifecycle-hooks"
    )
    public LifecycleHooksResponse lifecycleHooksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") final String groupName
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeLifecycleHooksRequest.class, LifecycleHooksResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLifecycleHooksResult result = client.describeLifecycleHooks(request.withAutoScalingGroupName(groupName));
            response.setLifecycleHooks(result.getLifecycleHooks());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "notificationConfigurations.list",
            path = "{region}/notification-configurations"
    )
    public NotificationConfigurationsResponse notificationConfigurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") @Nullable final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeNotificationConfigurationsRequest.class, NotificationConfigurationsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeNotificationConfigurationsResult result = client.describeNotificationConfigurations(request.withAutoScalingGroupNames(groupName).withNextToken(page));
            response.setNotificationConfigurations(result.getNotificationConfigurations());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scalingActivities.list",
            path = "{region}/scaling-activities"
    )
    public ScalingActivitiesResponse scalingActivitiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") @Nullable final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeScalingActivitiesRequest.class, ScalingActivitiesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingActivitiesResult result = client.describeScalingActivities(request.withAutoScalingGroupName(groupName).withNextToken(page));
            response.setActivities(result.getActivities());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scheduledActions.list",
            path = "{region}/scheduled-actions"
    )
    public ScheduledActionsResponse scheduledActionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("groupName") @Nullable final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingCaller.get(DescribeScheduledActionsRequest.class, ScheduledActionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScheduledActionsResult result = client.describeScheduledActions(request.withAutoScalingGroupName(groupName).withNextToken(page));
            response.setActions(result.getScheduledUpdateGroupActions());
            response.setNextPage(result.getNextToken());
        });
    }
}
