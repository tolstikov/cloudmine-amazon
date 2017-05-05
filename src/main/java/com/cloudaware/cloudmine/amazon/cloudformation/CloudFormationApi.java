package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.model.CreateStackRequest;
import com.amazonaws.services.cloudformation.model.CreateStackResult;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackEventsRequest;
import com.amazonaws.services.cloudformation.model.DescribeStackEventsResult;
import com.amazonaws.services.cloudformation.model.DescribeStackResourceRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksRequest;
import com.amazonaws.services.cloudformation.model.DescribeStacksResult;
import com.amazonaws.services.cloudformation.model.GetTemplateRequest;
import com.amazonaws.services.cloudformation.model.GetTemplateResult;
import com.amazonaws.services.cloudformation.model.ListStackResourcesRequest;
import com.amazonaws.services.cloudformation.model.ListStackResourcesResult;
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
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "cloudformation",
        canonicalName = "CloudFormation",
        title = "AWS CloudFormation",
        description = "Create and Manage Resources with Templates",
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
public final class CloudFormationApi {
    private static List<com.amazonaws.services.cloudformation.model.Tag> reconvertTagsCf(final Map<String, String> tags) {
        final List<com.amazonaws.services.cloudformation.model.Tag> out = Lists.newArrayList();
        if (tags == null) {
            return out;
        }
        for (final Map.Entry<String, String> e : tags.entrySet()) {
            final com.amazonaws.services.cloudformation.model.Tag tag = new com.amazonaws.services.cloudformation.model.Tag();
            tag.setKey(e.getKey());
            tag.setValue(e.getValue());
            out.add(tag);
        }
        return out;
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.list",
            path = "{region}/stacks"
    )
    public StacksResponse stacksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") @Nullable final String stackName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            final DescribeStacksResult response = clientWrapper.getClient().describeStacks(
                    new DescribeStacksRequest()
                            .withStackName(stackName)
                            .withNextToken(page)
            );
            return new StacksResponse(response.getStacks(), response.getNextToken());
        } catch (Throwable t) {
            return new StacksResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.templates.get",
            path = "{region}/stacks/{stackName}/template"
    )
    public StackTemplateResponse stackTemplatesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            final GetTemplateResult response = clientWrapper.getClient().getTemplate(new GetTemplateRequest().withStackName(stackName));
            return new StackTemplateResponse(response.getTemplateBody());
        } catch (Throwable t) {
            return new StackTemplateResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "stacks.create",
            path = "{region}/stacks"
    )
    public StackIdResponse stacksCreate(@Named("credentials") final String credentials, @Named("region") final String region, final StackRequest stackRequest) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            Preconditions.checkNotNull(stackRequest);
            final CreateStackRequest request = new CreateStackRequest();
            request.withCapabilities(stackRequest.getCapabilities());
            request.withDisableRollback(stackRequest.getDisableRollback());
            request.withNotificationARNs(stackRequest.getNotificationArns());
            request.withOnFailure(stackRequest.getOnFailure() == null ? null : stackRequest.getOnFailure().name());
            request.withParameters(stackRequest.getParameters());
            request.withStackName(stackRequest.getStackName());
            request.withTags(reconvertTagsCf(stackRequest.getTags()));
            request.withTemplateBody(stackRequest.getTemplateBody());
            request.withTemplateURL(stackRequest.getTemplateUrl());
            request.withTimeoutInMinutes(stackRequest.getTimeoutInMinutes());
            final CreateStackResult result = clientWrapper.getClient().createStack(request);
            return new StackIdResponse(result.getStackId());
        } catch (Throwable t) {
            return new StackIdResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "stacks.delete",
            path = "{region}/stacks/{stackName}"
    )
    public AmazonResponse stacksDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            if (Strings.isNullOrEmpty(stackName)) {
                throw new IllegalArgumentException("You need to set Stack Name property");
            }
            clientWrapper.getClient().deleteStack(new DeleteStackRequest().withStackName(stackName));
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.events.list",
            path = "{region}/stacks/{stackName}/events"
    )
    public StackEventsResponse stackEventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            final DescribeStackEventsResult response = clientWrapper.getClient().describeStackEvents(
                    new DescribeStackEventsRequest()
                            .withStackName(stackName)
                            .withNextToken(page)
            );
            return new StackEventsResponse(response.getStackEvents(), response.getNextToken());
        } catch (Throwable t) {
            return new StackEventsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.resources.list",
            path = "{region}/stacks/{stackName}/resources"
    )
    public StackResourcesResponse stackResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            final ListStackResourcesResult result = clientWrapper.getClient().listStackResources(
                    new ListStackResourcesRequest()
                            .withStackName(stackName)
                            .withNextToken(page)
            );
            return new StackResourcesResponse(result.getStackResourceSummaries(), result.getNextToken());
        } catch (Throwable t) {
            return new StackResourcesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stacks.resources.get",
            path = "{region}/stack-resources/by-logical-resource-id/{logicalResourceId}"
    )
    public StackResourceResponse stackResourcesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stackName") final String stackName,
            @Named("logicalResourceId") final String logicalResourceId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFormation> clientWrapper = new AmazonClientHelper(credentials).getCloudFormation(region)) {
            final DescribeStackResourceRequest request = new DescribeStackResourceRequest().withStackName(stackName).withLogicalResourceId(logicalResourceId);
            return new StackResourceResponse(clientWrapper.getClient().describeStackResource(request).getStackResourceDetail());
        } catch (Throwable t) {
            return new StackResourceResponse(AmazonResponse.parse(t));
        }
    }
}
