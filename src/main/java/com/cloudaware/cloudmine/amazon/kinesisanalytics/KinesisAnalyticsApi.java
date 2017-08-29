package com.cloudaware.cloudmine.amazon.kinesisanalytics;

import com.amazonaws.services.kinesisanalytics.model.DescribeApplicationRequest;
import com.amazonaws.services.kinesisanalytics.model.DescribeApplicationResult;
import com.amazonaws.services.kinesisanalytics.model.ListApplicationsRequest;
import com.amazonaws.services.kinesisanalytics.model.ListApplicationsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "kinesisanalytics",
        canonicalName = "KinesisAnalytics",
        title = "Amazon Kinesis Analytics",
        description = "With Amazon Kinesis Analytics, you can process and analyze streaming data using standard SQL",
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
public final class KinesisAnalyticsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applicationSummaries.list",
            path = "{region}/application-summaries"
    )
    public ApplicationSummariesResponse applicationSummariesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KinesisAnalyticsCaller.get(ListApplicationsRequest.class, ApplicationSummariesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListApplicationsResult result = client.listApplications(request.withExclusiveStartApplicationName(page));
            response.setApplications(result.getApplicationSummaries());
            if (result.isHasMoreApplications()) {
                response.setNextPage(result.getApplicationSummaries().get(result.getApplicationSummaries().size() - 1).getApplicationName());
            }
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.get",
            path = "{region}/applications/{applicationName}"
    )
    public ApplicationResponse applicationsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName
    ) throws AmazonUnparsedException {
        return KinesisAnalyticsCaller.get(DescribeApplicationRequest.class, ApplicationResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeApplicationResult result = client.describeApplication(
                    request
                            .withApplicationName(applicationName)
            );
            response.setApplication(result.getApplicationDetail());
        });
    }
}
