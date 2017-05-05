package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.services.support.AWSSupport;
import com.amazonaws.services.support.model.DescribeCasesRequest;
import com.amazonaws.services.support.model.DescribeCasesResult;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorCheckResultRequest;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorCheckResultResult;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorChecksRequest;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorChecksResult;
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
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "support",
        canonicalName = "Support",
        title = "AWS Support",
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
public final class SupportApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "cases.list",
            path = "cases"
    )
    public CasesResponse casesList(
            @Named("credentials") final String credentials,
            @Named("includeResolved") @Nullable final Boolean includeResolved,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSSupport> clientWrapper = new AmazonClientHelper(credentials).getAwsSupport()) {
            final DescribeCasesResult response = clientWrapper.getClient().describeCases(
                    new DescribeCasesRequest()
                            .withIncludeResolvedCases(includeResolved)
                            .withNextToken(page)
            );
            return new CasesResponse(response.getCases(), response.getNextToken());
        } catch (Throwable t) {
            return new CasesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trustedAdvisorChecks.list",
            path = "trusted-advisor-checks"
    )
    public TrustedAdvisorChecksResponse trustedAdvisorChecksList(
            @Named("credentials") final String credentials
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSSupport> clientWrapper = new AmazonClientHelper(credentials).getAwsSupport()) {
            final DescribeTrustedAdvisorChecksResult result = clientWrapper.getClient().describeTrustedAdvisorChecks(new DescribeTrustedAdvisorChecksRequest().withLanguage("en"));
            return new TrustedAdvisorChecksResponse(result.getChecks());
        } catch (Throwable t) {
            return new TrustedAdvisorChecksResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trustedAdvisorChecks.results.get",
            path = "trusted-advisor-checks/{checkId}"
    )
    public TrustedAdvisorCheckResultResponse trustedAdvisorChecksResultsGet(
            @Named("credentials") final String credentials,
            @Named("checkId") final String checkId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSSupport> clientWrapper = new AmazonClientHelper(credentials).getAwsSupport()) {
            final DescribeTrustedAdvisorCheckResultResult result = clientWrapper.getClient().describeTrustedAdvisorCheckResult(new DescribeTrustedAdvisorCheckResultRequest().withCheckId(checkId));
            return new TrustedAdvisorCheckResultResponse(result.getResult());
        } catch (Throwable t) {
            return new TrustedAdvisorCheckResultResponse(AmazonResponse.parse(t));
        }
    }

}
