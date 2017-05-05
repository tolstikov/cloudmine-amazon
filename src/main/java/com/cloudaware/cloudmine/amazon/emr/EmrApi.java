package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsResult;
import com.amazonaws.services.elasticmapreduce.model.Cluster;
import com.amazonaws.services.elasticmapreduce.model.DescribeClusterRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeStepRequest;
import com.amazonaws.services.elasticmapreduce.model.ListBootstrapActionsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListBootstrapActionsResult;
import com.amazonaws.services.elasticmapreduce.model.ListClustersRequest;
import com.amazonaws.services.elasticmapreduce.model.ListClustersResult;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceGroupsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceGroupsResult;
import com.amazonaws.services.elasticmapreduce.model.ListInstancesRequest;
import com.amazonaws.services.elasticmapreduce.model.ListInstancesResult;
import com.amazonaws.services.elasticmapreduce.model.ListStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListStepsResult;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowResult;
import com.amazonaws.services.elasticmapreduce.model.Step;
import com.amazonaws.services.elasticmapreduce.model.TerminateJobFlowsRequest;
import com.amazonaws.services.elasticmapreduce.model.TerminateJobFlowsResult;
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

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "emr",
        canonicalName = "Emr",
        title = "Amazon EMR",
        description = "Hosted Hadoop Framework",
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
public final class EmrApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/cluster"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final ListClustersResult result = clientWrapper.getClient().listClusters(
                    new ListClustersRequest()
                            .withMarker(page)
            );
            return new ClustersResponse(result.getClusters(), result.getMarker());
        } catch (Throwable t) {
            return new ClustersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.get",
            path = "{region}/clusters/{clusterId}"
    )
    public ClusterResponse clustersGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final Cluster cluster = clientWrapper.getClient().describeCluster(new DescribeClusterRequest().withClusterId(clusterId)).getCluster();
            return new ClusterResponse(cluster);
        } catch (Throwable t) {
            return new ClusterResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.bootstrapActions.list",
            path = "{region}/clusters/{clusterId}/bootstrap-actions"
    )
    public BootstrapActionsResponse clustersBootstrapActionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final ListBootstrapActionsResult result = clientWrapper.getClient().listBootstrapActions(
                    new ListBootstrapActionsRequest()
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            return new BootstrapActionsResponse(result.getBootstrapActions(), result.getMarker());
        } catch (Throwable t) {
            return new BootstrapActionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.instanceGroups.list",
            path = "{region}/clusters/{clusterId}/instance-groups"
    )
    public InstanceGroupsResponse clustersInstanceGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final ListInstanceGroupsResult result = clientWrapper.getClient().listInstanceGroups(
                    new ListInstanceGroupsRequest()
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            return new InstanceGroupsResponse(result.getInstanceGroups(), result.getMarker());
        } catch (Throwable t) {
            return new InstanceGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.instances.list",
            path = "{region}/clusters/{clusterId}/instances"
    )
    public InstancesResponse clustersInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final ListInstancesResult result = clientWrapper.getClient().listInstances(
                    new ListInstancesRequest()
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            return new InstancesResponse(result.getInstances(), result.getMarker());
        } catch (Throwable t) {
            return new InstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.steps.list",
            path = "{region}/clusters/{clusterId}/steps"
    )
    public StepsResponse clustersStepsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final ListStepsResult result = clientWrapper.getClient().listSteps(new ListStepsRequest()
                    .withClusterId(clusterId)
                    .withMarker(page)
            );
            return new StepsResponse(result.getSteps(), result.getMarker());
        } catch (Throwable t) {
            return new StepsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.steps.get",
            path = "{region}/clusters/{clusterId}/steps/{stepId}"
    )
    public StepResponse clustersStepsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("stepId") final String stepId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final Step step = clientWrapper.getClient().describeStep(new DescribeStepRequest().withClusterId(clusterId).withStepId(stepId)).getStep();
            return new StepResponse(step);
        } catch (Throwable t) {
            return new StepResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "jobFlows.run",
            path = "{region}/job-flows"
    )
    public JobFlowRunResponse jobFlowsRun(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final JobFlowRunRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final RunJobFlowRequest runJobFlowRequest = new RunJobFlowRequest();
            runJobFlowRequest.setName(request.getName());
            runJobFlowRequest.setLogUri(request.getLogUri());
            runJobFlowRequest.setAdditionalInfo(request.getAdditionalInfo());
            runJobFlowRequest.setAmiVersion(request.getAmiVersion());
            runJobFlowRequest.setReleaseLabel(request.getReleaseLabel());
            runJobFlowRequest.setInstances(request.getInstances());
            runJobFlowRequest.setSteps(request.getSteps());
            runJobFlowRequest.setBootstrapActions(request.getBootstrapActions());
            runJobFlowRequest.setSupportedProducts(request.getSupportedProducts());
            runJobFlowRequest.setNewSupportedProducts(request.getNewSupportedProducts());
            runJobFlowRequest.setApplications(request.getApplications());
            runJobFlowRequest.setConfigurations(request.getConfigurations());
            runJobFlowRequest.setVisibleToAllUsers(request.getVisibleToAllUsers());
            runJobFlowRequest.setJobFlowRole(request.getJobFlowRole());
            runJobFlowRequest.setServiceRole(request.getServiceRole());
            runJobFlowRequest.setTags(request.getTags());
            runJobFlowRequest.setSecurityConfiguration(request.getSecurityConfiguration());
            runJobFlowRequest.setAutoScalingRole(request.getAutoScalingRole());
            runJobFlowRequest.setScaleDownBehavior(request.getScaleDownBehavior());
            final RunJobFlowResult result = clientWrapper.getClient().runJobFlow(runJobFlowRequest);
            return new JobFlowRunResponse(result.getJobFlowId());
        } catch (Throwable t) {
            return new JobFlowRunResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "jobFlows.terminate",
            path = "{region}/job-flows"
    )
    public AmazonResponse jobFlowsTerminate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobFlowId") final List<String> jobFlowId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final TerminateJobFlowsResult terminateJobFlowsResult = clientWrapper.getClient().terminateJobFlows(new TerminateJobFlowsRequest().withJobFlowIds(jobFlowId));
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "jobFlows.steps.add",
            path = "{region}/job-flows/{jobFlowId}/steps"
    )
    public StepIdsResponse jobFlowsStepsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobFlowId") final String jobFlowId,
            final StepsRequest steps
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticMapReduce> clientWrapper = new AmazonClientHelper(credentials).getEmr(region)) {
            final AddJobFlowStepsResult addJobFlowStepsResult = clientWrapper.getClient().addJobFlowSteps(new AddJobFlowStepsRequest().withJobFlowId(jobFlowId).withSteps(steps.getStepConfigs()));
            return new StepIdsResponse(addJobFlowStepsResult.getStepIds());
        } catch (Throwable t) {
            return new StepIdsResponse(AmazonResponse.parse(t));
        }
    }

}
