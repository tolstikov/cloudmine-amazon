package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.DescribeTrailsRequest;
import com.amazonaws.services.cloudtrail.model.DescribeTrailsResult;
import com.amazonaws.services.cloudtrail.model.GetEventSelectorsRequest;
import com.amazonaws.services.cloudtrail.model.GetEventSelectorsResult;
import com.amazonaws.services.cloudtrail.model.GetTrailStatusRequest;
import com.amazonaws.services.cloudtrail.model.GetTrailStatusResult;
import com.amazonaws.services.cloudtrail.model.ListTagsRequest;
import com.amazonaws.services.cloudtrail.model.ListTagsResult;
import com.amazonaws.services.cloudtrail.model.LookupAttribute;
import com.amazonaws.services.cloudtrail.model.LookupEventsRequest;
import com.amazonaws.services.cloudtrail.model.LookupEventsResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.collect.Lists;

import java.util.List;

@Api(
        name = "cloudtrail",
        canonicalName = "CloudTrail",
        title = "AWS CloudTrail",
        description = "Track User Activity and API Usage",
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
public final class CloudTrailApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "events.lookup",
            path = "{region}/events/lookup"
    )
    public LookupResponse eventsLookup(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("pageSize") @Nullable final Integer pageSize,
            final LookupRequest lookupRequest
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(LookupEventsRequest.class, LookupResponse.class, credentials, region).execute((client, request, response) -> {
            request.setStartTime(lookupRequest.getStartTime());
            request.setEndTime(lookupRequest.getEndTime());
            if (lookupRequest.getLookupAttributes() != null) {
                final List<LookupAttribute> attributes = Lists.newArrayList();
                for (final LookupRequest.Attribute attr : lookupRequest.getLookupAttributes()) {
                    attributes.add(new LookupAttribute().withAttributeKey(attr.getAttributeKey()).withAttributeValue(attr.getAttributeValue()));
                }
                request.setLookupAttributes(attributes);
            }

            final LookupEventsResult result = client.lookupEvents(
                    request
                            .withNextToken(page)
                            .withMaxResults(pageSize)
            );
            response.setEvents(result.getEvents());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trails.list",
            path = "{region}/trails"
    )
    public TrailsResponse trailsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(DescribeTrailsRequest.class, TrailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTrailsResult result = client.describeTrails(request);
            response.setTrails(result.getTrailList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trails.statuses.get",
            path = "{region}/trails/NAME/status"
    )
    public TrailStatusResponse trailsStatusesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("name") final String name
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(GetTrailStatusRequest.class, TrailStatusResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTrailStatusResult result = client.getTrailStatus(
                    request.withName(name)
            );
            response.setTrailStatus(result);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trails.eventSelectors.get",
            path = "{region}/trails/{trailName}/event-selectors"
    )
    public TrailEventSelectorsResponse trailsEventSelectorsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("trailArn") final String trailName
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(GetEventSelectorsRequest.class, TrailEventSelectorsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetEventSelectorsResult result = client.getEventSelectors(
                    request.withTrailName(trailName)
            );
            response.setTrailArn(result.getTrailARN());
            response.setEventsSelectors(result.getEventSelectors());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "resources.tags.list",
            path = "{region}/resources/tags"
    )
    public TagsResponse resourcesTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArns") final List<String> resourceArns,
            @Named("page") @Nullable final String page

    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(ListTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsResult result = client.listTags(
                    request
                            .withResourceIdList(resourceArns)
                            .withNextToken(page)
            );
            response.setTags(result.getResourceTagList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.add",
            path = "{region}/resources/{resourceArn}/tags/add"
    )
    public AmazonResponse resourcesTagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            final AddTagsRequest request
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(com.amazonaws.services.cloudtrail.model.AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.addTags(r.withResourceId(resourceArn).withTagsList(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "resources.tags.remove",
            path = "{region}/resources/{resourceArn}/tags/remove"
    )
    public AmazonResponse resourcesTagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            final RemoveTagsRequest request
    ) throws AmazonUnparsedException {
        return CloudTrailCaller.get(com.amazonaws.services.cloudtrail.model.RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTags(r.withResourceId(resourceArn).withTagsList(request.getTags()));
        });
    }
}
