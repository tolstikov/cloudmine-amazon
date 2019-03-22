package com.cloudaware.cloudmine.amazon.costandusagereport;

import com.amazonaws.services.costandusagereport.model.DescribeReportDefinitionsRequest;
import com.amazonaws.services.costandusagereport.model.DescribeReportDefinitionsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "costandusagereport",
        canonicalName = "CostAndUsageReport",
        title = "AWS Cost And Usage Report Service",
        description = "The AWS Cost and Usage report tracks your AWS usage and provides estimated charges associated with your AWS account",
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
public final class CostAndUsageReportApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reportDefinitions.list",
            path = "report-definitions"
    )
    public ReportDefinitionsResponse reportDefinitionsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CostAndUsageReportCaller.get(DescribeReportDefinitionsRequest.class, ReportDefinitionsResponse.class, credentials).execute((client, request, response) -> {
            final DescribeReportDefinitionsResult result = client.describeReportDefinitions(request.withNextToken(page));
            response.setReportDefinitions(result.getReportDefinitions());
            response.setNextPage(result.getNextToken());
        });
    }

}
