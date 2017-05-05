package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.model.DescribeClustersRequest;
import com.amazonaws.services.ecs.model.DescribeClustersResult;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesRequest;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesResult;
import com.amazonaws.services.ecs.model.DescribeServicesRequest;
import com.amazonaws.services.ecs.model.DescribeServicesResult;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionRequest;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionResult;
import com.amazonaws.services.ecs.model.DescribeTasksRequest;
import com.amazonaws.services.ecs.model.DescribeTasksResult;
import com.amazonaws.services.ecs.model.ListClustersRequest;
import com.amazonaws.services.ecs.model.ListClustersResult;
import com.amazonaws.services.ecs.model.ListContainerInstancesRequest;
import com.amazonaws.services.ecs.model.ListContainerInstancesResult;
import com.amazonaws.services.ecs.model.ListServicesRequest;
import com.amazonaws.services.ecs.model.ListServicesResult;
import com.amazonaws.services.ecs.model.ListTaskDefinitionsRequest;
import com.amazonaws.services.ecs.model.ListTaskDefinitionsResult;
import com.amazonaws.services.ecs.model.ListTasksRequest;
import com.amazonaws.services.ecs.model.ListTasksResult;
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
        name = "ecs",
        canonicalName = "Ecs",
        title = "Amazon EC2 Container Service",
        description = "Run and Manage Docker Containers",
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
public final class EcsApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clustersArns.list",
            path = "{region}/cluster-arns"
    )
    public ArnsResponse clustersArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final ListClustersResult result = clientWrapper.getClient().listClusters(
                    new ListClustersRequest().withNextToken(page)
            );
            return new ArnsResponse(result.getClusterArns(), result.getNextToken());
        } catch (Throwable t) {
            return new ArnsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final DescribeClustersResult result = clientWrapper.getClient().describeClusters(new DescribeClustersRequest().withClusters(arns));
            return new ClustersResponse(result.getClusters());
        } catch (Throwable t) {
            return new ClustersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.containerInstances.list",
            path = "{region}/clusters/CLUSTER_ARN/container-instances"
    )
    public ContainerInstancesResponse clustersContainerInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final DescribeContainerInstancesResult result = clientWrapper.getClient()
                    .describeContainerInstances(new DescribeContainerInstancesRequest().withCluster(clusterArn).withContainerInstances(arns));
            return new ContainerInstancesResponse(result.getContainerInstances());
        } catch (Throwable t) {
            return new ContainerInstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.containerInstanceArns.list",
            path = "{region}/clusters/CLUSTER_ARN/container-instance-arns"
    )
    public ArnsResponse clustersContainerInstanceArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final ListContainerInstancesResult result = clientWrapper.getClient().listContainerInstances(
                    new ListContainerInstancesRequest()
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            return new ArnsResponse(result.getContainerInstanceArns(), result.getNextToken());
        } catch (Throwable t) {
            return new ArnsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.serviceArns.list",
            path = "{region}/clusters/CLUSTER_ARN/service-arns"
    )
    public ArnsResponse clustersServiceArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final ListServicesResult result = clientWrapper.getClient().listServices(
                    new ListServicesRequest()
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            return new ArnsResponse(result.getServiceArns(), result.getNextToken());
        } catch (Throwable t) {
            return new ArnsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.services.list",
            path = "{region}/clusters/CLUSTER_ARN/services"
    )
    public ServicesResponse clustersServicesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final DescribeServicesResult result = clientWrapper.getClient().describeServices(new DescribeServicesRequest().withCluster(clusterArn).withServices(arns));
            return new ServicesResponse(result.getServices());
        } catch (Throwable t) {
            return new ServicesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.taskArns.list",
            path = "{region}/clusters/CLUSTER_ARN/task-arns"
    )
    public ArnsResponse clustersTaskArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final ListTasksResult result = clientWrapper.getClient().listTasks(
                    new ListTasksRequest()
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            return new ArnsResponse(result.getTaskArns(), result.getNextToken());
        } catch (Throwable t) {
            return new ArnsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.tasks.list",
            path = "{region}/clusters/CLUSTER_ARN/tasks"
    )
    public TasksResponse getTasks(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final DescribeTasksResult result = clientWrapper.getClient().describeTasks(new DescribeTasksRequest().withCluster(clusterArn).withTasks(arns));
            return new TasksResponse(result.getTasks());
        } catch (Throwable t) {
            return new TasksResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "taskDefinitionArns.list",
            path = "{region}/task-definition-arns"
    )
    public ArnsResponse taskDefinitionArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final ListTaskDefinitionsResult result = clientWrapper.getClient().listTaskDefinitions(
                    new ListTaskDefinitionsRequest()
                            .withNextToken(page)
            );
            return new ArnsResponse(result.getTaskDefinitionArns(), result.getNextToken());
        } catch (Throwable t) {
            return new ArnsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "taskDefinitions.get",
            path = "{region}/task-definition/ARN"
    )
    public TaskDefinitionResponse taskDefinitionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonECS> clientWrapper = new AmazonClientHelper(credentials).getEcs(region)) {
            final DescribeTaskDefinitionResult result = clientWrapper.getClient().describeTaskDefinition(new DescribeTaskDefinitionRequest().withTaskDefinition(arn));
            return new TaskDefinitionResponse(result.getTaskDefinition());
        } catch (Throwable t) {
            return new TaskDefinitionResponse(AmazonResponse.parse(t));
        }
    }
}
