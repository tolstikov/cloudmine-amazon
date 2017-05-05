package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.AmazonCloudFront;
import com.amazonaws.services.cloudfront.model.GetCloudFrontOriginAccessIdentityRequest;
import com.amazonaws.services.cloudfront.model.GetCloudFrontOriginAccessIdentityResult;
import com.amazonaws.services.cloudfront.model.GetDistributionRequest;
import com.amazonaws.services.cloudfront.model.GetDistributionResult;
import com.amazonaws.services.cloudfront.model.GetInvalidationRequest;
import com.amazonaws.services.cloudfront.model.GetInvalidationResult;
import com.amazonaws.services.cloudfront.model.GetStreamingDistributionRequest;
import com.amazonaws.services.cloudfront.model.GetStreamingDistributionResult;
import com.amazonaws.services.cloudfront.model.ListCloudFrontOriginAccessIdentitiesRequest;
import com.amazonaws.services.cloudfront.model.ListCloudFrontOriginAccessIdentitiesResult;
import com.amazonaws.services.cloudfront.model.ListDistributionsRequest;
import com.amazonaws.services.cloudfront.model.ListDistributionsResult;
import com.amazonaws.services.cloudfront.model.ListInvalidationsRequest;
import com.amazonaws.services.cloudfront.model.ListInvalidationsResult;
import com.amazonaws.services.cloudfront.model.ListStreamingDistributionsRequest;
import com.amazonaws.services.cloudfront.model.ListStreamingDistributionsResult;
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
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "cloudfront",
        canonicalName = "CloudFront",
        title = "Amazon CloudFront",
        description = "Global Content Delivery Network",
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
public final class CloudFrontApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.list",
            path = "distributions"
    )
    public DistributionsResponse distributionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final ListDistributionsResult result = clientWrapper.getClient().listDistributions(
                    new ListDistributionsRequest()
                            .withMarker(page)
            );
            return new DistributionsResponse(result.getDistributionList().getItems(), result.getDistributionList().getNextMarker());
        } catch (Throwable t) {
            return new DistributionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.get",
            path = "distributions/{id}"
    )
    public DistributionResponse distributionsGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final GetDistributionResult result = clientWrapper.getClient().getDistribution(new GetDistributionRequest(id));
            return new DistributionResponse(result.getDistribution());
        } catch (Throwable t) {
            return new DistributionResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streamingDistributions.list",
            path = "streaming-distributions"
    )
    public StreamingDistributionsResponse streamingDistributionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final ListStreamingDistributionsResult result = clientWrapper.getClient().listStreamingDistributions(
                    new ListStreamingDistributionsRequest()
                            .withMarker(page)
            );
            return new StreamingDistributionsResponse(result.getStreamingDistributionList().getItems(), result.getStreamingDistributionList().getNextMarker());
        } catch (Throwable t) {
            return new StreamingDistributionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streamingDistributions.get",
            path = "streaming-distributions/{id}"
    )
    public StreamingDistributionResponse streamingDistributionsGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final GetStreamingDistributionResult result = clientWrapper.getClient().getStreamingDistribution(new GetStreamingDistributionRequest(id));
            return new StreamingDistributionResponse(result.getStreamingDistribution());
        } catch (Throwable t) {
            return new StreamingDistributionResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.invalidations.list",
            path = "distributions/{distributionId}/invalidations"
    )
    public InvalidationsResponse distributionsInvalidationsList(
            @Named("credentials") final String credentials,
            @Named("distributionId") final String distributionId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final ListInvalidationsResult result = clientWrapper.getClient().listInvalidations(
                    new ListInvalidationsRequest()
                            .withDistributionId(distributionId)
                            .withMarker(page)
            );
            return new InvalidationsResponse(result.getInvalidationList().getItems(), result.getInvalidationList().getNextMarker());
        } catch (Throwable t) {
            return new InvalidationsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "distributions.invalidations.get",
            path = "distributions/{distributionId}/invalidations/{id}"
    )
    public InvalidationResponse distributionsInvalidationsGet(
            @Named("credentials") final String credentials,
            @Named("distributionId") final String distributionId,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final GetInvalidationResult result = clientWrapper.getClient().getInvalidation(new GetInvalidationRequest(distributionId, id));
            return new InvalidationResponse(result.getInvalidation());
        } catch (Throwable t) {
            return new InvalidationResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "originAccessIdentities.list",
            path = "origin-access-identities"
    )
    public OriginAccessIdentitiesResponse originAccessIdentitiesList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final ListCloudFrontOriginAccessIdentitiesResult result = clientWrapper.getClient().listCloudFrontOriginAccessIdentities(
                    new ListCloudFrontOriginAccessIdentitiesRequest()
                            .withMarker(page)
            );
            return new OriginAccessIdentitiesResponse(result.getCloudFrontOriginAccessIdentityList().getItems(), result.getCloudFrontOriginAccessIdentityList().getNextMarker());
        } catch (Throwable t) {
            return new OriginAccessIdentitiesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "originAccessIdentities.get",
            path = "origin-access-identities/{id}"
    )
    public OriginAccessIdentityResponse originAccessIdentitiesGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudFront> clientWrapper = new AmazonClientHelper(credentials).getCloudFront()) {
            final GetCloudFrontOriginAccessIdentityResult result = clientWrapper.getClient().getCloudFrontOriginAccessIdentity(new GetCloudFrontOriginAccessIdentityRequest(id));
            return new OriginAccessIdentityResponse(result.getCloudFrontOriginAccessIdentity());
        } catch (Throwable t) {
            return new OriginAccessIdentityResponse(AmazonResponse.parse(t));
        }
    }
}
