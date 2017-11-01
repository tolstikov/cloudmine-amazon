package com.cloudaware.cloudmine.amazon.stepfunctions;

import com.amazonaws.services.stepfunctions.model.DescribeActivityRequest;
import com.amazonaws.services.stepfunctions.model.DescribeActivityResult;
import com.amazonaws.services.stepfunctions.model.DescribeExecutionRequest;
import com.amazonaws.services.stepfunctions.model.DescribeExecutionResult;
import com.amazonaws.services.stepfunctions.model.DescribeStateMachineRequest;
import com.amazonaws.services.stepfunctions.model.DescribeStateMachineResult;
import com.amazonaws.services.stepfunctions.model.ListActivitiesRequest;
import com.amazonaws.services.stepfunctions.model.ListActivitiesResult;
import com.amazonaws.services.stepfunctions.model.ListExecutionsRequest;
import com.amazonaws.services.stepfunctions.model.ListExecutionsResult;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesRequest;
import com.amazonaws.services.stepfunctions.model.ListStateMachinesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "stepfunctions",
        canonicalName = "StepFunctions",
        title = "AWS Step Functions",
        description = "AWS Step Functions makes it easy to coordinate the components of distributed applications as a series of steps in a visual workflow.",
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
public final class StepFunctionsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stateMachines.list",
            path = "{region}/state-machines"
    )
    public StateMachinesResponse stateMachinesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(ListStateMachinesRequest.class, StateMachinesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListStateMachinesResult result = client.listStateMachines(request.withNextToken(page));
            response.setStateMachines(result.getStateMachines());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stateMachines.get",
            path = "{region}/state-machines/{stateMachineArn}"
    )
    public StateMachineResponse stateMachinesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stateMachineArn") final String stateMachineArn
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(DescribeStateMachineRequest.class, StateMachineResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStateMachineResult result = client.describeStateMachine(request.withStateMachineArn(stateMachineArn));
            response.setCreationDate(result.getCreationDate());
            response.setDefinition(result.getDefinition());
            response.setName(result.getName());
            response.setRoleArn(result.getRoleArn());
            response.setStateMachineArn(result.getStateMachineArn());
            response.setStatus(result.getStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "stateMachines.executions.list",
            path = "{region}/state-machines/{stateMachineArn}/executions"
    )
    public ExecutionsResponse stateMachinesExecutionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("stateMachineArn") final String stateMachineArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(ListExecutionsRequest.class, ExecutionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListExecutionsResult result = client.listExecutions(
                    request
                            .withStateMachineArn(stateMachineArn)
                            .withNextToken(page)
            );
            response.setExecutions(result.getExecutions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "executions.get",
            path = "{region}/executions/{executionArn}"
    )
    public ExecutionResponse executionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("executionArn") final String executionArn
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(DescribeExecutionRequest.class, ExecutionResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeExecutionResult result = client.describeExecution(request.withExecutionArn(executionArn));
            response.setExecutionArn(result.getExecutionArn());
            response.setInput(result.getInput());
            response.setName(result.getName());
            response.setOutput(result.getOutput());
            response.setStartDate(result.getStartDate());
            response.setStateMachineArn(result.getStateMachineArn());
            response.setStatus(result.getStatus());
            response.setStopDate(result.getStopDate());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "activities.list",
            path = "{region}/activities"
    )
    public ActivitiesResponse activitiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(ListActivitiesRequest.class, ActivitiesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListActivitiesResult result = client.listActivities(request.withNextToken(page));
            response.setActivities(result.getActivities());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "activities.get",
            path = "{region}/activities/{activityArn}"
    )
    public ActivityResponse activitiesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("activityArn") final String activityArn
    ) throws AmazonUnparsedException {
        return StepFunctionsCaller.get(DescribeActivityRequest.class, ActivityResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeActivityResult result = client.describeActivity(request.withActivityArn(activityArn));
            response.setActivityArn(result.getActivityArn());
            response.setCreationDate(result.getCreationDate());
            response.setName(result.getName());
        });
    }
}
