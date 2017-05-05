package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.AWSCloudTrail;
import com.amazonaws.services.cloudtrail.model.DescribeTrailsResult;
import com.amazonaws.services.cloudtrail.model.GetTrailStatusRequest;
import com.amazonaws.services.cloudtrail.model.LookupAttribute;
import com.amazonaws.services.cloudtrail.model.LookupEventsRequest;
import com.amazonaws.services.cloudtrail.model.LookupEventsResult;
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
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
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
        try (ClientWrapper<AWSCloudTrail> clientWrapper = new AmazonClientHelper(credentials).getCloudTrail(region)) {
            final LookupEventsRequest request = new LookupEventsRequest();
            request.setStartTime(lookupRequest.getStartTime());
            request.setEndTime(lookupRequest.getEndTime());
            if (lookupRequest.getLookupAttributes() != null) {
                final List<LookupAttribute> attributes = Lists.newArrayList();
                for (final LookupRequest.Attribute attr : lookupRequest.getLookupAttributes()) {
                    attributes.add(new LookupAttribute().withAttributeKey(attr.getAttributeKey()).withAttributeValue(attr.getAttributeValue()));
                }
                request.setLookupAttributes(attributes);
            }
            final LookupEventsResult result = clientWrapper.getClient().lookupEvents(
                    request
                            .withNextToken(page)
                            .withMaxResults(pageSize)
            );
            return new LookupResponse(result.getEvents(), result.getNextToken());
        } catch (Throwable t) {
            return new LookupResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trails.list",
            path = "{region}/trails"
    )
    public TrailsResponse trailsList(@Named("credentials") final String credentials, @Named("region") final String region) throws AmazonUnparsedException {
        try (ClientWrapper<AWSCloudTrail> clientWrapper = new AmazonClientHelper(credentials).getCloudTrail(region)) {
            final DescribeTrailsResult response = clientWrapper.getClient().describeTrails();
            return new TrailsResponse(response.getTrailList());
        } catch (Throwable t) {
            return new TrailsResponse(AmazonResponse.parse(t));
        }
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
        try (ClientWrapper<AWSCloudTrail> clientWrapper = new AmazonClientHelper(credentials).getCloudTrail(region)) {
            return new TrailStatusResponse(clientWrapper.getClient().getTrailStatus(new GetTrailStatusRequest().withName(name)));
        } catch (Throwable t) {
            return new TrailStatusResponse(AmazonResponse.parse(t));
        }
    }
}
