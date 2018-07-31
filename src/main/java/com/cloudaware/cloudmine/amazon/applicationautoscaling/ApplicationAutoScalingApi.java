package com.cloudaware.cloudmine.amazon.applicationautoscaling;

import com.amazonaws.services.applicationautoscaling.model.DescribeScalableTargetsRequest;
import com.amazonaws.services.applicationautoscaling.model.DescribeScalableTargetsResult;
import com.amazonaws.services.applicationautoscaling.model.DescribeScalingActivitiesRequest;
import com.amazonaws.services.applicationautoscaling.model.DescribeScalingActivitiesResult;
import com.amazonaws.services.applicationautoscaling.model.DescribeScalingPoliciesRequest;
import com.amazonaws.services.applicationautoscaling.model.DescribeScalingPoliciesResult;
import com.amazonaws.services.applicationautoscaling.model.DescribeScheduledActionsRequest;
import com.amazonaws.services.applicationautoscaling.model.DescribeScheduledActionsResult;
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
        name = "applicationautoscaling",
        canonicalName = "ApplicationAutoScaling",
        title = "Application Auto Scaling",
        description = "With Application Auto Scaling, you can configure automatic scaling for your scalable resources",
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
public final class ApplicationAutoScalingApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scalableTargets.list",
            path = "{region}/scalable-targets/{serviceNamespace}"
    )
    public ScalableTargetsResponse scalableTargetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("serviceNamespace") @Description("ecs | elasticmapreduce | ec2 | appstream | dynamodb | rds | sagemaker | custom-resource") final String serviceNamespace,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApplicationAutoScalingCaller.get(DescribeScalableTargetsRequest.class, ScalableTargetsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalableTargetsResult result = client.describeScalableTargets(
                    request.withNextToken(page).withServiceNamespace(serviceNamespace)
            );
            response.setScalableTargets(result.getScalableTargets());
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
            @Named("serviceNamespace") @Description("ecs | elasticmapreduce | ec2 | appstream | dynamodb | rds | sagemaker | custom-resource") final String serviceNamespace,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApplicationAutoScalingCaller.get(DescribeScalingActivitiesRequest.class, ScalingActivitiesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingActivitiesResult result = client.describeScalingActivities(
                    request.withServiceNamespace(serviceNamespace).withNextToken(page)
            );
            response.setScalingActivities(result.getScalingActivities());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scalingPolicies.list",
            path = "{region}/scaling-policies"
    )
    public ScalingPoliciesResponse scalingPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("serviceNamespace") @Description("ecs | elasticmapreduce | ec2 | appstream | dynamodb | rds | sagemaker | custom-resource") final String serviceNamespace,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApplicationAutoScalingCaller.get(DescribeScalingPoliciesRequest.class, ScalingPoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingPoliciesResult result = client.describeScalingPolicies(
                    request.withServiceNamespace(serviceNamespace).withNextToken(page)
            );
            response.setScalingPolicies(result.getScalingPolicies());
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
            @Named("serviceNamespace") @Description("ecs | elasticmapreduce | ec2 | appstream | dynamodb | rds | sagemaker | custom-resource") final String serviceNamespace,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ApplicationAutoScalingCaller.get(DescribeScheduledActionsRequest.class, ScheduledActionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScheduledActionsResult result = client.describeScheduledActions(
                    request.withServiceNamespace(serviceNamespace).withNextToken(page)
            );
            response.setScheduledActions(result.getScheduledActions());
            response.setNextPage(result.getNextToken());
        });
    }

}
