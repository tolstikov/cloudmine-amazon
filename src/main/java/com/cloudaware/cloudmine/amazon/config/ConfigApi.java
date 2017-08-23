package com.cloudaware.cloudmine.amazon.config;

import com.amazonaws.services.config.model.DescribeComplianceByConfigRuleRequest;
import com.amazonaws.services.config.model.DescribeComplianceByConfigRuleResult;
import com.amazonaws.services.config.model.DescribeConfigRuleEvaluationStatusRequest;
import com.amazonaws.services.config.model.DescribeConfigRuleEvaluationStatusResult;
import com.amazonaws.services.config.model.DescribeConfigRulesRequest;
import com.amazonaws.services.config.model.DescribeConfigRulesResult;
import com.amazonaws.services.config.model.DescribeConfigurationRecorderStatusRequest;
import com.amazonaws.services.config.model.DescribeConfigurationRecorderStatusResult;
import com.amazonaws.services.config.model.DescribeConfigurationRecordersRequest;
import com.amazonaws.services.config.model.DescribeConfigurationRecordersResult;
import com.amazonaws.services.config.model.DescribeDeliveryChannelStatusRequest;
import com.amazonaws.services.config.model.DescribeDeliveryChannelStatusResult;
import com.amazonaws.services.config.model.DescribeDeliveryChannelsRequest;
import com.amazonaws.services.config.model.DescribeDeliveryChannelsResult;
import com.amazonaws.services.config.model.GetComplianceDetailsByConfigRuleRequest;
import com.amazonaws.services.config.model.GetComplianceDetailsByConfigRuleResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "config",
        canonicalName = "Config",
        title = "AWS Config",
        description = "AWS Config is a service that enables you to assess, audit, and evaluate the configurations of your AWS resources",
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
public final class ConfigApi {

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
        return ConfigCaller.get(DescribeConfigRulesRequest.class, RulesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigRulesResult result = client.describeConfigRules(request.withNextToken(page));
            response.setRules(result.getConfigRules());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "ruleEvaluationStatuses.list",
            path = "{region}/rule-evaluation-statuses"
    )
    public RuleEvaluationStatusesResponse ruleEvaluationStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeConfigRuleEvaluationStatusRequest.class, RuleEvaluationStatusesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigRuleEvaluationStatusResult result = client.describeConfigRuleEvaluationStatus(request.withNextToken(page));
            response.setRuleEvaluationStatuses(result.getConfigRulesEvaluationStatus());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "ruleCompliances.list",
            path = "{region}/rule-compliances"
    )
    public RuleCompliancesResponse ruleCompliancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeComplianceByConfigRuleRequest.class, RuleCompliancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeComplianceByConfigRuleResult result = client.describeComplianceByConfigRule(request.withNextToken(page));
            response.setRuleCompliances(result.getComplianceByConfigRules());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.complianceDetails.list",
            path = "{region}/rules/{ruleName}/compliance-details"
    )
    public ComplianceDetailsResponse rulesComplianceDetailsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("ruleName") final String ruleName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(GetComplianceDetailsByConfigRuleRequest.class, ComplianceDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetComplianceDetailsByConfigRuleResult result = client.getComplianceDetailsByConfigRule(
                    request
                            .withConfigRuleName(ruleName)
                            .withNextToken(page)
            );
            response.setEvaluationResults(result.getEvaluationResults());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationRecorders.list",
            path = "{region}/configuration-recorders"
    )
    public ConfigurationRecordersResponse configurationRecordersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeConfigurationRecordersRequest.class, ConfigurationRecordersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationRecordersResult result = client.describeConfigurationRecorders(request);
            response.setConfigurationRecorders(result.getConfigurationRecorders());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationRecordersStatus.list",
            path = "{region}/configuration-recorders-status"
    )
    public ConfigurationRecordersStatusResponse configurationRecordersStatusList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeConfigurationRecorderStatusRequest.class, ConfigurationRecordersStatusResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationRecorderStatusResult result = client.describeConfigurationRecorderStatus(request);
            response.setConfigurationRecordersStatus(result.getConfigurationRecordersStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deliveryChannels.list",
            path = "{region}/delivery-channels"
    )
    public DeliveryChannelsResponse deliveryChannelsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeDeliveryChannelsRequest.class, DeliveryChannelsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDeliveryChannelsResult result = client.describeDeliveryChannels(request);
            response.setDeliveryChannels(result.getDeliveryChannels());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deliveryChannelsStatus.list",
            path = "{region}/delivery-channels-status"
    )
    public DeliveryChannelsStatusResponse deliveryChannelsStatusList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ConfigCaller.get(DescribeDeliveryChannelStatusRequest.class, DeliveryChannelsStatusResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDeliveryChannelStatusResult result = client.describeDeliveryChannelStatus(request);
            response.setDeliveryChannelsStatus(result.getDeliveryChannelsStatus());
        });
    }
}
