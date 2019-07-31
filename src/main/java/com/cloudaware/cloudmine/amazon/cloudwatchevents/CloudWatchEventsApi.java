package com.cloudaware.cloudmine.amazon.cloudwatchevents;

import com.amazonaws.services.cloudwatchevents.model.DescribeEventBusRequest;
import com.amazonaws.services.cloudwatchevents.model.DescribeEventBusResult;
import com.amazonaws.services.cloudwatchevents.model.DescribeRuleRequest;
import com.amazonaws.services.cloudwatchevents.model.DescribeRuleResult;
import com.amazonaws.services.cloudwatchevents.model.ListRulesRequest;
import com.amazonaws.services.cloudwatchevents.model.ListRulesResult;
import com.amazonaws.services.cloudwatchevents.model.ListTargetsByRuleRequest;
import com.amazonaws.services.cloudwatchevents.model.ListTargetsByRuleResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "cloudwatchevents",
        canonicalName = "CloudWatchEvents",
        title = "Amazon CloudWatch Events",
        description = "Amazon CloudWatch Events delivers a near real-time stream of system events that describe changes",
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
public final class CloudWatchEventsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.list",
            path = "{region}/rules"
    )
    public RulesResponse rulesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchEventsCaller.get(ListRulesRequest.class, RulesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListRulesResult result = client.listRules(
                    request.withNextToken(page)
            );
            response.setRules(result.getRules());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "eventBuses.get",
            path = "{region}/event-buses"
    )
    public EventBusResponse eventBusesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return CloudWatchEventsCaller.get(DescribeEventBusRequest.class, EventBusResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventBusResult result = client.describeEventBus(request);
            response.setArn(result.getArn());
            response.setName(result.getArn());
            response.setPolicy(result.getPolicy());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targets.list",
            path = "{region}/targets/{ruleName}"
    )
    public TargetsResponse targetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("ruleName") final String ruleName
    ) throws AmazonUnparsedException {
        return CloudWatchEventsCaller.get(ListTargetsByRuleRequest.class, TargetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTargetsByRuleResult result = client.listTargetsByRule(request.withNextToken(page).withRule(ruleName));
            response.setTargets(result.getTargets());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.get",
            path = "{region}/rules/{ruleName}"
    )
    public RuleResponse rulesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("ruleName") final String ruleName
    ) throws AmazonUnparsedException {
        return CloudWatchEventsCaller.get(DescribeRuleRequest.class, RuleResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRuleResult result = client.describeRule(request.withName(ruleName));
            response.setArn(result.getArn());
            response.setDescription(result.getDescription());
            response.setEventPattern(result.getEventPattern());
            response.setName(result.getName());
            response.setRoleArn(result.getRoleArn());
            response.setScheduleExpression(result.getScheduleExpression());
            response.setState(result.getState());
        });
    }

}
