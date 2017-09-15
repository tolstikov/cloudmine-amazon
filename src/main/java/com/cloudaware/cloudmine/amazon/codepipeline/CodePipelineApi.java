package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.ActionTypeId;
import com.amazonaws.services.codepipeline.model.GetJobDetailsRequest;
import com.amazonaws.services.codepipeline.model.GetJobDetailsResult;
import com.amazonaws.services.codepipeline.model.GetPipelineExecutionRequest;
import com.amazonaws.services.codepipeline.model.GetPipelineExecutionResult;
import com.amazonaws.services.codepipeline.model.GetPipelineRequest;
import com.amazonaws.services.codepipeline.model.GetPipelineResult;
import com.amazonaws.services.codepipeline.model.GetPipelineStateRequest;
import com.amazonaws.services.codepipeline.model.GetPipelineStateResult;
import com.amazonaws.services.codepipeline.model.GetThirdPartyJobDetailsRequest;
import com.amazonaws.services.codepipeline.model.GetThirdPartyJobDetailsResult;
import com.amazonaws.services.codepipeline.model.ListActionTypesRequest;
import com.amazonaws.services.codepipeline.model.ListActionTypesResult;
import com.amazonaws.services.codepipeline.model.ListPipelineExecutionsRequest;
import com.amazonaws.services.codepipeline.model.ListPipelineExecutionsResult;
import com.amazonaws.services.codepipeline.model.ListPipelinesRequest;
import com.amazonaws.services.codepipeline.model.ListPipelinesResult;
import com.amazonaws.services.codepipeline.model.PollForJobsRequest;
import com.amazonaws.services.codepipeline.model.PollForJobsResult;
import com.amazonaws.services.codepipeline.model.PollForThirdPartyJobsRequest;
import com.amazonaws.services.codepipeline.model.PollForThirdPartyJobsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "codepipeline",
        canonicalName = "CodePipeline",
        title = "AWS CodePipeline",
        description = "AWS CodePipeline is a continuous integration and continuous delivery service for fast and reliable application and infrastructure updates",
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
public final class CodePipelineApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.list",
            path = "{region}/pipelines"
    )
    public PipelinesResponse pipelinesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(ListPipelinesRequest.class, PipelinesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPipelinesResult result = client.listPipelines(request.withNextToken(page));
            response.setPipelines(result.getPipelines());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.get",
            path = "{region}/pipelines/{pipelineName}"
    )
    public PipelineResponse pipelinesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineName") final String pipelineName,
            @Named("version") @Nullable final Integer version
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(GetPipelineRequest.class, PipelineResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPipelineResult result = client.getPipeline(
                    request
                            .withName(pipelineName)
                            .withVersion(version)
            );
            response.setPipeline(result.getPipeline());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.pipelineExecutions.list",
            path = "{region}/pipelines/{pipelineName}/pipeline-executions"
    )
    public PipelineExecutionsResponse pipelinesPipelineExecutionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineName") final String pipelineName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(ListPipelineExecutionsRequest.class, PipelineExecutionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPipelineExecutionsResult result = client.listPipelineExecutions(
                    request
                            .withPipelineName(pipelineName)
                            .withNextToken(page)
            );
            response.setPipelineExecutions(result.getPipelineExecutionSummaries());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.pipelineExecutions.get",
            path = "{region}/pipelines/{pipelineName}/pipeline-executions/{pipelineExecutionId}"
    )
    public PipelineExecutionResponse pipelinesPipelineExecutionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineName") final String pipelineName,
            @Named("pipelineExecutionId") final String pipelineExecutionId
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(GetPipelineExecutionRequest.class, PipelineExecutionResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPipelineExecutionResult result = client.getPipelineExecution(
                    request
                            .withPipelineName(pipelineName)
                            .withPipelineExecutionId(pipelineExecutionId)
            );
            response.setPipelineExecution(result.getPipelineExecution());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "actionTypes.list",
            path = "{region}/action-types"
    )
    public ActionTypesResponse actionTypesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("actionOwnerFilter") @Nullable final String actionOwnerFilter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(ListActionTypesRequest.class, ActionTypesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListActionTypesResult result = client.listActionTypes(
                    request
                            .withActionOwnerFilter(actionOwnerFilter)
                            .withNextToken(page)
            );
            response.setActionTypes(result.getActionTypes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.pipelineStates.get",
            path = "{region}/pipelines/{pipelineName}/pipeline-states"
    )
    public PipelineStateResponse pipelinesPipelineStatesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineName") final String pipelineName
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(GetPipelineStateRequest.class, PipelineStateResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPipelineStateResult result = client.getPipelineState(request.withName(pipelineName));
            response.setCreated(result.getCreated());
            response.setPipelineName(result.getPipelineName());
            response.setPipelineVersion(result.getPipelineVersion());
            response.setStageStates(result.getStageStates());
            response.setUpdated(result.getUpdated());
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
            @Named("category") final String category,
            @Named("owner") final String owner,
            @Named("provider") final String provider,
            @Named("version") final String version
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(PollForJobsRequest.class, JobsResponse.class, credentials, region).execute((client, request, response) -> {
            final PollForJobsResult result = client.pollForJobs(
                    request
                            .withActionTypeId(
                                    new ActionTypeId()
                                            .withCategory(category)
                                            .withOwner(owner)
                                            .withProvider(provider)
                                            .withVersion(version))

            );
            response.setJobs(result.getJobs());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "thirdPartyJobs.list",
            path = "{region}/third-party-jobs"
    )
    public ThirdPartyJobsResponse thirdPartyJobsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("category") final String category,
            @Named("owner") final String owner,
            @Named("provider") final String provider,
            @Named("version") final String version
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(PollForThirdPartyJobsRequest.class, ThirdPartyJobsResponse.class, credentials, region).execute((client, request, response) -> {
            final PollForThirdPartyJobsResult result = client.pollForThirdPartyJobs(
                    request
                            .withActionTypeId(
                                    new ActionTypeId()
                                            .withCategory(category)
                                            .withOwner(owner)
                                            .withProvider(provider)
                                            .withVersion(version))

            );
            response.setThirdPartyJobs(result.getJobs());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobs.get",
            path = "{region}/jobs/{jobId}"
    )
    public JobResponse jobsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobId") final String jobId
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(GetJobDetailsRequest.class, JobResponse.class, credentials, region).execute((client, request, response) -> {
            final GetJobDetailsResult result = client.getJobDetails(request.withJobId(jobId));
            response.setJobDetails(result.getJobDetails());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "thirdPartyJobs.get",
            path = "{region}/third-party-jobs/{jobId}"
    )
    public ThirdPartyJobResponse thirdPartyJobsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobId") final String jobId,
            @Named("clientToken") final String clientToken
    ) throws AmazonUnparsedException {
        return CodePipelineCaller.get(GetThirdPartyJobDetailsRequest.class, ThirdPartyJobResponse.class, credentials, region).execute((client, request, response) -> {
            final GetThirdPartyJobDetailsResult result = client.getThirdPartyJobDetails(
                    request
                            .withJobId(jobId)
                            .withClientToken(clientToken)
            );
            response.setThirdPartyJobDetails(result.getJobDetails());
        });
    }
}
