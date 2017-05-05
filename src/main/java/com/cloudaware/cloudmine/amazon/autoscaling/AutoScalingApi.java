package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.AmazonAutoScaling;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsRequest;
import com.amazonaws.services.autoscaling.model.DescribeAutoScalingGroupsResult;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsRequest;
import com.amazonaws.services.autoscaling.model.DescribeLaunchConfigurationsResult;
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
        try (ClientWrapper<AmazonAutoScaling> clientWrapper = new AmazonClientHelper(credentials).getAutoScaling(region)) {
            final DescribeAutoScalingGroupsResult response = clientWrapper.getClient()
                    .describeAutoScalingGroups(
                            new DescribeAutoScalingGroupsRequest()
                                    .withAutoScalingGroupNames(groupNames)
                                    .withNextToken(page)
                    );
            return new AutoScalingGroupsResponse(response.getAutoScalingGroups(), response.getNextToken());
        } catch (Throwable t) {
            return new AutoScalingGroupsResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AmazonAutoScaling> clientWrapper = new AmazonClientHelper(credentials).getAutoScaling(region)) {
            final DescribeLaunchConfigurationsResult response = clientWrapper.getClient().describeLaunchConfigurations(
                    new DescribeLaunchConfigurationsRequest()
                            .withNextToken(page)
            );
            return new LaunchConfigurationsResponse(response.getLaunchConfigurations(), response.getNextToken());
        } catch (Throwable t) {
            return new LaunchConfigurationsResponse(AmazonResponse.parse(t));
        }
    }
}

