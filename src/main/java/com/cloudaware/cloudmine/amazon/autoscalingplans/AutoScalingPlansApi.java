package com.cloudaware.cloudmine.amazon.autoscalingplans;

import com.amazonaws.services.autoscalingplans.model.DescribeScalingPlanResourcesRequest;
import com.amazonaws.services.autoscalingplans.model.DescribeScalingPlanResourcesResult;
import com.amazonaws.services.autoscalingplans.model.DescribeScalingPlansRequest;
import com.amazonaws.services.autoscalingplans.model.DescribeScalingPlansResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "autoscalingplans",
        canonicalName = "AutoScalingPlans",
        title = "AWS Auto Scaling",
        description = "Use AWS Auto Scaling to quickly discover all the scalable AWS resources for your application and configure dynamic scaling for your scalable resources",
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
public final class AutoScalingPlansApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scalingPlans.list",
            path = "{region}/scaling-plans"
    )
    public ScalingPlansResponse scalingPlansList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingPlansCaller.get(DescribeScalingPlansRequest.class, ScalingPlansResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingPlansResult result = client.describeScalingPlans(
                    request.withNextToken(page)
            );
            response.setScalingPlans(result.getScalingPlans());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "scalingPlans.resources.list",
            path = "{region}/scaling-plans/{scalingPlanName}/{scalingPlanVersion}/resources"
    )
    public ScalingPlanResourcesResponse scalingPlansResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("scalingPlanName") final String scalingPlanName,
            @Named("scalingPlanVersion") final Long scalingPlanVersion,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AutoScalingPlansCaller.get(DescribeScalingPlanResourcesRequest.class, ScalingPlanResourcesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingPlanResourcesResult result = client.describeScalingPlanResources(
                    request.withScalingPlanName(scalingPlanName).withScalingPlanVersion(scalingPlanVersion).withNextToken(page)
            );
            response.setScalingPlanResources(result.getScalingPlanResources());
            response.setNextPage(result.getNextToken());
        });
    }

}
