package com.cloudaware.cloudmine.amazon.waf;

import com.amazonaws.services.waf.model.GetByteMatchSetRequest;
import com.amazonaws.services.waf.model.GetByteMatchSetResult;
import com.amazonaws.services.waf.model.GetIPSetRequest;
import com.amazonaws.services.waf.model.GetIPSetResult;
import com.amazonaws.services.waf.model.GetRateBasedRuleRequest;
import com.amazonaws.services.waf.model.GetRateBasedRuleResult;
import com.amazonaws.services.waf.model.GetRuleRequest;
import com.amazonaws.services.waf.model.GetRuleResult;
import com.amazonaws.services.waf.model.GetSizeConstraintSetRequest;
import com.amazonaws.services.waf.model.GetSizeConstraintSetResult;
import com.amazonaws.services.waf.model.GetSqlInjectionMatchSetRequest;
import com.amazonaws.services.waf.model.GetSqlInjectionMatchSetResult;
import com.amazonaws.services.waf.model.GetWebACLRequest;
import com.amazonaws.services.waf.model.GetWebACLResult;
import com.amazonaws.services.waf.model.GetXssMatchSetRequest;
import com.amazonaws.services.waf.model.GetXssMatchSetResult;
import com.amazonaws.services.waf.model.ListByteMatchSetsRequest;
import com.amazonaws.services.waf.model.ListByteMatchSetsResult;
import com.amazonaws.services.waf.model.ListIPSetsRequest;
import com.amazonaws.services.waf.model.ListIPSetsResult;
import com.amazonaws.services.waf.model.ListRateBasedRulesRequest;
import com.amazonaws.services.waf.model.ListRateBasedRulesResult;
import com.amazonaws.services.waf.model.ListResourcesForWebACLRequest;
import com.amazonaws.services.waf.model.ListResourcesForWebACLResult;
import com.amazonaws.services.waf.model.ListRulesRequest;
import com.amazonaws.services.waf.model.ListRulesResult;
import com.amazonaws.services.waf.model.ListSizeConstraintSetsRequest;
import com.amazonaws.services.waf.model.ListSizeConstraintSetsResult;
import com.amazonaws.services.waf.model.ListSqlInjectionMatchSetsRequest;
import com.amazonaws.services.waf.model.ListSqlInjectionMatchSetsResult;
import com.amazonaws.services.waf.model.ListWebACLsRequest;
import com.amazonaws.services.waf.model.ListWebACLsResult;
import com.amazonaws.services.waf.model.ListXssMatchSetsRequest;
import com.amazonaws.services.waf.model.ListXssMatchSetsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.ByteBufferTransformer;
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
        name = "waf",
        canonicalName = "Waf",
        title = "AWS Web Application Firewall (WAF)",
        description = "Web Application Firewall",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE,
        transformers = {
                ByteBufferTransformer.class
        }
)
public final class WafApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "webacls.list",
            path = "webacls"
    )
    public WebAclsResponse webAclList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListWebACLsRequest.class, WebAclsResponse.class, credentials).execute((client, request, response) -> {
            final ListWebACLsResult result = client.listWebACLs(request.withNextMarker(page));
            response.setWebAclSummaries(result.getWebACLs());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "webacls.get",
            path = "webacls/{id}"
    )
    public WebAclResponse webAclGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetWebACLRequest.class, WebAclResponse.class, credentials).execute((client, request, response) -> {
            final GetWebACLResult result = client.getWebACL(request.withWebACLId(id));
            response.setWebAcl(result.getWebACL());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.list",
            path = "rules"
    )
    public RulesResponse ruleList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListRulesRequest.class, RulesResponse.class, credentials).execute((client, request, response) -> {
            final ListRulesResult result = client.listRules(request.withNextMarker(page));
            response.setRuleSummaries(result.getRules());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.get",
            path = "rules/{id}"
    )
    public RuleResponse ruleGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetRuleRequest.class, RuleResponse.class, credentials).execute((client, request, response) -> {
            final GetRuleResult result = client.getRule(request.withRuleId(id));
            response.setRule(result.getRule());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rateBasedRules.list",
            path = "rate-based-rules"
    )
    public RateBasedRulesResponse rateBasedRuleList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListRateBasedRulesRequest.class, RateBasedRulesResponse.class, credentials).execute((client, request, response) -> {
            final ListRateBasedRulesResult result = client.listRateBasedRules(request.withNextMarker(page));
            response.setRateBasedRuleSummaries(result.getRules());
            request.setNextMarker(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rateBasedRules.get",
            path = "rate-based-rules/{id}"
    )
    public RateBasedRuleResponse rateBasedRuleGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetRateBasedRuleRequest.class, RateBasedRuleResponse.class, credentials).execute((client, request, response) -> {
            final GetRateBasedRuleResult result = client.getRateBasedRule(request.withRuleId(id));
            response.setRateBasedRule(result.getRule());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "ipSets.list",
            path = "ip-sets"
    )
    public IpSetsResponse ipSetList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListIPSetsRequest.class, IpSetsResponse.class, credentials).execute((client, request, response) -> {
            final ListIPSetsResult result = client.listIPSets(request.withNextMarker(page));
            response.setIpSetSummaries(result.getIPSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "ipSets.get",
            path = "ip-sets/{id}"
    )
    public IpSetResponse ipSetGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetIPSetRequest.class, IpSetResponse.class, credentials).execute((client, request, response) -> {
            final GetIPSetResult result = client.getIPSet(request.withIPSetId(id));
            response.setIpSet(result.getIPSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "byteMatchSets.list",
            path = "byte-match-sets"
    )
    public ByteMatchSetsResponse byteMatchSetList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListByteMatchSetsRequest.class, ByteMatchSetsResponse.class, credentials).execute((client, request, response) -> {
            final ListByteMatchSetsResult result = client.listByteMatchSets(request.withNextMarker(page));
            response.setByteMatchSetSummaries(result.getByteMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "byteMatchSets.get",
            path = "byte-match-sets/{id}"
    )
    public ByteMatchSetResponse byteMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetByteMatchSetRequest.class, ByteMatchSetResponse.class, credentials).execute((client, request, response) -> {
            final GetByteMatchSetResult result = client.getByteMatchSet(request.withByteMatchSetId(id));
            response.setByteMatchSet(result.getByteMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sqlInjectionMatchSets.list",
            path = "sql-injection-match-sets"
    )
    public SqlInjectionMatchSetsResponse sqlInjectionMatchSetList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListSqlInjectionMatchSetsRequest.class, SqlInjectionMatchSetsResponse.class, credentials).execute((client, request, response) -> {
            final ListSqlInjectionMatchSetsResult result = client.listSqlInjectionMatchSets(request.withNextMarker(page));
            response.setSqlInjectionMatchSetSummaries(result.getSqlInjectionMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sqlInjectionMatchSets.get",
            path = "sql-injection-match-sets/{id}"
    )
    public SqlInjectionMatchSetResponse sqlInjectionMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetSqlInjectionMatchSetRequest.class, SqlInjectionMatchSetResponse.class, credentials).execute((client, request, response) -> {
            final GetSqlInjectionMatchSetResult result = client.getSqlInjectionMatchSet(request.withSqlInjectionMatchSetId(id));
            response.setSqlInjectionMatchSet(result.getSqlInjectionMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "xssMatchSets.list",
            path = "xss-match-sets"
    )
    public XssMatchSetsResponse xssMatchSetList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListXssMatchSetsRequest.class, XssMatchSetsResponse.class, credentials).execute((client, request, response) -> {
            final ListXssMatchSetsResult result = client.listXssMatchSets(request.withNextMarker(page));
            response.setXssMatchSetSummaries(result.getXssMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "xssMatchSets.get",
            path = "xss-match-sets/{id}"
    )
    public XssMatchSetResponse xssMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetXssMatchSetRequest.class, XssMatchSetResponse.class, credentials).execute((client, request, response) -> {
            final GetXssMatchSetResult result = client.getXssMatchSet(request.withXssMatchSetId(id));
            response.setXssMatchSet(result.getXssMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sizeConstraintSets.list",
            path = "size-constraint-sets"
    )
    public SizeConstraintSetsResponse sizeConstraintSetList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.get(ListSizeConstraintSetsRequest.class, SizeConstraintSetsResponse.class, credentials).execute((client, request, response) -> {
            final ListSizeConstraintSetsResult result = client.listSizeConstraintSets(request.withNextMarker(page));
            response.setSizeConstraintSetSummaries(result.getSizeConstraintSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sizeConstraintSets.get",
            path = "size-constraint-sets/{id}"
    )
    public SizeConstraintSetResponse sizeConstraintSetGet(
            @Named("credentials") final String credentials,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.get(GetSizeConstraintSetRequest.class, SizeConstraintSetResponse.class, credentials).execute((client, request, response) -> {
            final GetSizeConstraintSetResult result = client.getSizeConstraintSet(request.withSizeConstraintSetId(id));
            response.setSizeConstraintSet(result.getSizeConstraintSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.webacls.list",
            path = "regions/{region}/webacls"
    )
    public WebAclsResponse regionsWebAclList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListWebACLsRequest.class, WebAclsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListWebACLsResult result = client.listWebACLs(request.withNextMarker(page));
            response.setWebAclSummaries(result.getWebACLs());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.webacls.resource.list",
            path = "regions/{region}/webacls/{id}/resources"
    )
    public WebAclResourceArnsResponse regionsWebAclResourceArnList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListResourcesForWebACLRequest.class, WebAclResourceArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListResourcesForWebACLResult result = client.listResourcesForWebACL(request.withWebACLId(id));
            response.setResourceArns(result.getResourceArns());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.webacls.get",
            path = "regions/{region}/webacls/{id}"
    )
    public WebAclResponse regionsWebAclGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetWebACLRequest.class, WebAclResponse.class, credentials, region).execute((client, request, response) -> {
            final GetWebACLResult result = client.getWebACL(request.withWebACLId(id));
            response.setWebAcl(result.getWebACL());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.rules.list",
            path = "regions/{region}/rules"
    )
    public RulesResponse regionsRuleList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListRulesRequest.class, RulesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListRulesResult result = client.listRules(request.withNextMarker(page));
            response.setRuleSummaries(result.getRules());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.rules.get",
            path = "regions/{region}/rules/{id}"
    )
    public RuleResponse regionsRuleGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetRuleRequest.class, RuleResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRuleResult result = client.getRule(request.withRuleId(id));
            response.setRule(result.getRule());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.rateBasedRules.list",
            path = "regions/{region}/rate-based-rules"
    )
    public RateBasedRulesResponse regionsRateBasedRuleList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListRateBasedRulesRequest.class, RateBasedRulesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListRateBasedRulesResult result = client.listRateBasedRules(request.withNextMarker(page));
            response.setRateBasedRuleSummaries(result.getRules());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.rateBasedRules.get",
            path = "regions/{region}/rate-based-rules/{id}"
    )
    public RateBasedRuleResponse regionsRateBasedRuleGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetRateBasedRuleRequest.class, RateBasedRuleResponse.class, credentials, region).execute((client, request, response) -> {
            final GetRateBasedRuleResult result = client.getRateBasedRule(request.withRuleId(id));
            response.setRateBasedRule(result.getRule());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.ipSets.list",
            path = "regions/{region}/ip-sets"
    )
    public IpSetsResponse regionsIpSetList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListIPSetsRequest.class, IpSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListIPSetsResult result = client.listIPSets(request.withNextMarker(page));
            response.setIpSetSummaries(result.getIPSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.ipSets.get",
            path = "regions/{region}/ip-sets/{id}"
    )
    public IpSetResponse regionsIpSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetIPSetRequest.class, IpSetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIPSetResult result = client.getIPSet(request.withIPSetId(id));
            response.setIpSet(result.getIPSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.byteMatchSets.list",
            path = "regions/{region}/byte-match-sets"
    )
    public ByteMatchSetsResponse regionsByteMatchSetList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListByteMatchSetsRequest.class, ByteMatchSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListByteMatchSetsResult result = client.listByteMatchSets(request.withNextMarker(page));
            response.setByteMatchSetSummaries(result.getByteMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.byteMatchSets.get",
            path = "regions/{region}/byte-match-sets/{id}"
    )
    public ByteMatchSetResponse regionsByteMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetByteMatchSetRequest.class, ByteMatchSetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetByteMatchSetResult result = client.getByteMatchSet(request.withByteMatchSetId(id));
            response.setByteMatchSet(result.getByteMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.sqlInjectionMatchSets.list",
            path = "regions/{region}/sql-injection-match-sets"
    )
    public SqlInjectionMatchSetsResponse regionsSqlInjectionMatchSetList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListSqlInjectionMatchSetsRequest.class, SqlInjectionMatchSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListSqlInjectionMatchSetsResult result = client.listSqlInjectionMatchSets(request.withNextMarker(page));
            response.setSqlInjectionMatchSetSummaries(result.getSqlInjectionMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.sqlInjectionMatchSets.get",
            path = "regions/{region}/sql-injection-match-sets/{id}"
    )
    public SqlInjectionMatchSetResponse regionsSqlInjectionMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetSqlInjectionMatchSetRequest.class, SqlInjectionMatchSetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSqlInjectionMatchSetResult result = client.getSqlInjectionMatchSet(request.withSqlInjectionMatchSetId(id));
            response.setSqlInjectionMatchSet(result.getSqlInjectionMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.xssMatchSets.list",
            path = "regions/{region}/xss-match-sets"
    )
    public XssMatchSetsResponse regionsXssMatchSetList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListXssMatchSetsRequest.class, XssMatchSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListXssMatchSetsResult result = client.listXssMatchSets(request.withNextMarker(page));
            response.setXssMatchSetSummaries(result.getXssMatchSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.xssMatchSets.get",
            path = "regions/{region}/xss-match-sets/{id}"
    )
    public XssMatchSetResponse regionsXssMatchSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetXssMatchSetRequest.class, XssMatchSetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetXssMatchSetResult result = client.getXssMatchSet(request.withXssMatchSetId(id));
            response.setXssMatchSet(result.getXssMatchSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.sizeConstraintSets.list",
            path = "regions/{region}/size-constraint-sets"
    )
    public SizeConstraintSetsResponse regionsSizeConstraintSetList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(ListSizeConstraintSetsRequest.class, SizeConstraintSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListSizeConstraintSetsResult result = client.listSizeConstraintSets(request.withNextMarker(page));
            response.setSizeConstraintSetSummaries(result.getSizeConstraintSets());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "regions.sizeConstraintSets.get",
            path = "regions/{region}/size-constraint-sets/{id}"
    )
    public SizeConstraintSetResponse regionsSizeConstraintSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("id") final String id
    ) throws AmazonUnparsedException {
        return WafCaller.getRegional(GetSizeConstraintSetRequest.class, SizeConstraintSetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSizeConstraintSetResult result = client.getSizeConstraintSet(request.withSizeConstraintSetId(id));
            response.setSizeConstraintSet(result.getSizeConstraintSet());
        });
    }
}
