package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.AmazonCloudSearch;
import com.amazonaws.services.cloudsearchv2.model.DescribeAnalysisSchemesRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeAvailabilityOptionsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeExpressionsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeIndexFieldsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeScalingParametersRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeServiceAccessPoliciesRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeSuggestersRequest;
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

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "cloudsearch",
        canonicalName = "Cloud Search",
        title = "Amazon CloudSearch",
        description = "Managed Search Service",
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
public final class CloudSearchApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domainNames.list",
            path = "{region}/domain-names"
    )
    public DomainNamesResponse domainNamesList(@Named("credentials") final String credentials, @Named("region") final String region) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new DomainNamesResponse(clientWrapper.getClient().listDomainNames().getDomainNames());
        } catch (Throwable t) {
            return new DomainNamesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.list",
            path = "{region}/domains"
    )
    public DomainsResponse domainsList(@Named("credentials") final String credentials, @Named("region") final String region) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new DomainsResponse(clientWrapper.getClient().describeDomains().getDomainStatusList());
        } catch (Throwable t) {
            return new DomainsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.suggesters.list",
            path = "{region}/domains/{domainName}/suggesters"
    )
    public SuggestersResponse domainsSuggestersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new SuggestersResponse(
                    clientWrapper.getClient().describeSuggesters(new DescribeSuggestersRequest().withDomainName(domainName)).getSuggesters()
            );
        } catch (Throwable t) {
            return new SuggestersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.expressions.list",
            path = "{region}/domains/{domainName}/expressions"
    )
    public ExpressionsResponse domainExpressionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new ExpressionsResponse(
                    clientWrapper.getClient().describeExpressions(new DescribeExpressionsRequest().withDomainName(domainName)).getExpressions()
            );
        } catch (Throwable t) {
            return new ExpressionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.analysisSchemes.list",
            path = "{region}/domains/{domainName}/analysis-schemes"
    )
    public AnalysisSchemesResponse domainsAnalysisSchemesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new AnalysisSchemesResponse(
                    clientWrapper.getClient().describeAnalysisSchemes(new DescribeAnalysisSchemesRequest().withDomainName(domainName)).getAnalysisSchemes()
            );
        } catch (Throwable t) {
            return new AnalysisSchemesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.indexFields.list",
            path = "{region}/domains/{domainName}/index-fields"
    )
    public IndexFieldsResponse domainsIndexFieldsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new IndexFieldsResponse(
                    clientWrapper.getClient().describeIndexFields(new DescribeIndexFieldsRequest().withDomainName(domainName)).getIndexFields()
            );
        } catch (Throwable t) {
            return new IndexFieldsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.scalingParameters.list",
            path = "{region}/domains/{domainName}/scaling-parameters"
    )
    public ScalingParametersResponse domainsScalingParametersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new ScalingParametersResponse(
                    clientWrapper.getClient().describeScalingParameters(new DescribeScalingParametersRequest().withDomainName(domainName)).getScalingParameters()
            );
        } catch (Throwable t) {
            return new ScalingParametersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.accessPolicies.list",
            path = "{region}/domains/{domainName}/access-policies"
    )
    public AccessPoliciesResponse domainsAccessPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new AccessPoliciesResponse(
                    clientWrapper.getClient().describeServiceAccessPolicies(new DescribeServiceAccessPoliciesRequest().withDomainName(domainName)).getAccessPolicies()
            );
        } catch (Throwable t) {
            return new AccessPoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.availabilityOptions.list",
            path = "{region}/domains/{domainName}/availability-options"
    )
    public AvailabilityOptionsResponse domainsAvailabilityOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonCloudSearch> clientWrapper = new AmazonClientHelper(credentials).getCloudSearch(region)) {
            return new AvailabilityOptionsResponse(
                    clientWrapper.getClient().describeAvailabilityOptions(new DescribeAvailabilityOptionsRequest().withDomainName(domainName)).getAvailabilityOptions()
            );
        } catch (Throwable t) {
            return new AvailabilityOptionsResponse(AmazonResponse.parse(t));
        }
    }
}
