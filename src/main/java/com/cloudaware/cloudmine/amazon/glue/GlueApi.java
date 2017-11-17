package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.GetClassifiersRequest;
import com.amazonaws.services.glue.model.GetClassifiersResult;
import com.amazonaws.services.glue.model.GetConnectionsRequest;
import com.amazonaws.services.glue.model.GetConnectionsResult;
import com.amazonaws.services.glue.model.GetCrawlersRequest;
import com.amazonaws.services.glue.model.GetCrawlersResult;
import com.amazonaws.services.glue.model.GetDatabasesRequest;
import com.amazonaws.services.glue.model.GetDatabasesResult;
import com.amazonaws.services.glue.model.GetDevEndpointsRequest;
import com.amazonaws.services.glue.model.GetDevEndpointsResult;
import com.amazonaws.services.glue.model.GetJobRunsRequest;
import com.amazonaws.services.glue.model.GetJobRunsResult;
import com.amazonaws.services.glue.model.GetJobsRequest;
import com.amazonaws.services.glue.model.GetJobsResult;
import com.amazonaws.services.glue.model.GetTablesRequest;
import com.amazonaws.services.glue.model.GetTablesResult;
import com.amazonaws.services.glue.model.GetTriggersRequest;
import com.amazonaws.services.glue.model.GetTriggersResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "glue",
        canonicalName = "Glue",
        title = "AWS Glue",
        description = "Simple, flexible, and cost-effective ETL",
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
public final class GlueApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "databases.list",
            path = "{region}/databases"
    )
    public DatabasesResponse databasesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetDatabasesRequest.class, DatabasesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDatabasesResult result = client.getDatabases(
                    request
                            .withNextToken(page)
            );
            response.setDatabases(result.getDatabaseList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "databases.tables.list",
            path = "{region}/databases/{databaseName}/tables"
    )
    public TablesResponse databasesTablesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("databaseName") final String databaseName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetTablesRequest.class, TablesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTablesResult result = client.getTables(
                    request
                            .withDatabaseName(databaseName)
                            .withNextToken(page)
            );
            response.setTables(result.getTableList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "connections.list",
            path = "{region}/connections"
    )
    public ConnectionsResponse connectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetConnectionsRequest.class, ConnectionsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetConnectionsResult result = client.getConnections(
                    request
                            .withNextToken(page)
            );
            response.setConnections(result.getConnectionList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "classifiers.list",
            path = "{region}/classifiers"
    )
    public ClassifiersResponse classifiersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetClassifiersRequest.class, ClassifiersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetClassifiersResult result = client.getClassifiers(request.withNextToken(page));
            response.setClassifiers(result.getClassifiers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "crawlers.list",
            path = "{region}/crawlers"
    )
    public CrawlersResponse crawlersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetCrawlersRequest.class, CrawlersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetCrawlersResult result = client.getCrawlers(request.withNextToken(page));
            response.setCrawlers(result.getCrawlers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobs.list",
            path = "{region}/jobs"
    )
    public JobsResponse jobsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetJobsRequest.class, JobsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetJobsResult result = client.getJobs(request.withNextToken(page));
            response.setJobs(result.getJobs());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobs.jobRuns.list",
            path = "{region}/jobs/{jobName}/job-runs"
    )
    public JobRunsResponse jobsJobRunsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobName") final String jobName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetJobRunsRequest.class, JobRunsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetJobRunsResult result = client.getJobRuns(
                    request
                            .withJobName(jobName)
                            .withNextToken(page)
            );
            response.setJobRuns(result.getJobRuns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "triggers.list",
            path = "{region}/triggers"
    )
    public TriggersResponse triggersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetTriggersRequest.class, TriggersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTriggersResult result = client.getTriggers(request.withNextToken(page));
            response.setTriggers(result.getTriggers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "devEndpoints.list",
            path = "{region}/dev-endpoints"
    )
    public DevEndpointsResponse devEndpointsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlueCaller.get(GetDevEndpointsRequest.class, DevEndpointsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDevEndpointsResult result = client.getDevEndpoints(request.withNextToken(page));
            response.setDevEndpoints(result.getDevEndpoints());
            response.setNextPage(result.getNextToken());
        });
    }
}
