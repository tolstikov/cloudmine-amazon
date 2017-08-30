package com.cloudaware.cloudmine.amazon.shield;

import com.amazonaws.services.shield.model.DescribeAttackRequest;
import com.amazonaws.services.shield.model.DescribeAttackResult;
import com.amazonaws.services.shield.model.DescribeSubscriptionRequest;
import com.amazonaws.services.shield.model.DescribeSubscriptionResult;
import com.amazonaws.services.shield.model.ListAttacksRequest;
import com.amazonaws.services.shield.model.ListAttacksResult;
import com.amazonaws.services.shield.model.ListProtectionsRequest;
import com.amazonaws.services.shield.model.ListProtectionsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "shield",
        canonicalName = "Shield",
        title = "AWS Shield Advanced",
        description = "AWS Shield Advanced",
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
public final class ShieldApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "protections.list",
            path = "protections"
    )
    public ProtectionsResponse protectionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ShieldCaller.get(ListProtectionsRequest.class, ProtectionsResponse.class, credentials).execute((client, request, response) -> {
            final ListProtectionsResult result = client.listProtections(request.withNextToken(page));
            response.setProtections(result.getProtections());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subscription.get",
            path = "subscription"
    )
    public SubscriptionResponse subscriptionGet(
            @Named("credentials") final String credentials
    ) throws AmazonUnparsedException {
        return ShieldCaller.get(DescribeSubscriptionRequest.class, SubscriptionResponse.class, credentials).execute((client, request, response) -> {
            final DescribeSubscriptionResult result = client.describeSubscription(request);
            response.setSubscription(result.getSubscription());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "attacks.list",
            path = "attacks"
    )
    public AttacksResponse attacksList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ShieldCaller.get(ListAttacksRequest.class, AttacksResponse.class, credentials).execute((client, request, response) -> {
            final ListAttacksResult result = client.listAttacks(request.withNextToken(page));
            response.setAttackSummaries(result.getAttackSummaries());
            request.setNextToken(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "attacks.get",
            path = "attacks/{id}"
    )
    public AttackResponse attacksGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return ShieldCaller.get(DescribeAttackRequest.class, AttackResponse.class, credentials).execute((client, request, response) -> {
            final DescribeAttackResult result = client.describeAttack(request.withAttackId(id));
            response.setAttackDetail(result.getAttack());
        });
    }

}
