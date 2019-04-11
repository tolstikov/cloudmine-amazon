package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.DescribeMeshRequest;
import com.amazonaws.services.appmesh.model.DescribeMeshResult;
import com.amazonaws.services.appmesh.model.DescribeRouteRequest;
import com.amazonaws.services.appmesh.model.DescribeRouteResult;
import com.amazonaws.services.appmesh.model.DescribeVirtualNodeRequest;
import com.amazonaws.services.appmesh.model.DescribeVirtualNodeResult;
import com.amazonaws.services.appmesh.model.DescribeVirtualRouterRequest;
import com.amazonaws.services.appmesh.model.DescribeVirtualRouterResult;
import com.amazonaws.services.appmesh.model.DescribeVirtualServiceRequest;
import com.amazonaws.services.appmesh.model.DescribeVirtualServiceResult;
import com.amazonaws.services.appmesh.model.ListMeshesRequest;
import com.amazonaws.services.appmesh.model.ListMeshesResult;
import com.amazonaws.services.appmesh.model.ListRoutesRequest;
import com.amazonaws.services.appmesh.model.ListRoutesResult;
import com.amazonaws.services.appmesh.model.ListTagsForResourceRequest;
import com.amazonaws.services.appmesh.model.ListTagsForResourceResult;
import com.amazonaws.services.appmesh.model.ListVirtualNodesRequest;
import com.amazonaws.services.appmesh.model.ListVirtualNodesResult;
import com.amazonaws.services.appmesh.model.ListVirtualRoutersRequest;
import com.amazonaws.services.appmesh.model.ListVirtualRoutersResult;
import com.amazonaws.services.appmesh.model.ListVirtualServicesRequest;
import com.amazonaws.services.appmesh.model.ListVirtualServicesResult;
import com.amazonaws.services.appmesh.model.TagResourceRequest;
import com.amazonaws.services.appmesh.model.UntagResourceRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

@Api(
        name = "appmesh",
        canonicalName = "AppMesh",
        title = "Amazon App Mesh",
        description = "AWS App Mesh is a service mesh based on the Envoy proxy that makes it easy to monitor and control microservices.",
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
public final class AppMeshApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.list",
            path = "{region}/meshes"
    )
    public MeshesResponse meshesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListMeshesRequest.class, MeshesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListMeshesResult result = client.listMeshes(request.withNextToken(page));
            response.setItems(result.getMeshes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.get",
            path = "{region}/meshes/{meshName}"
    )
    public MeshResponse meshGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(DescribeMeshRequest.class, MeshResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeMeshResult result = client.describeMesh(request.withMeshName(meshName));
            response.setMesh(result.getMesh());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualRouters.list",
            path = "{region}/meshes/{meshName}/virtual-routers"
    )
    public VirtualRoutersResponse meshesVirtualRoutersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListVirtualRoutersRequest.class, VirtualRoutersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVirtualRoutersResult result = client.listVirtualRouters(request.withMeshName(meshName).withNextToken(page));
            response.setItems(result.getVirtualRouters());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualRouters.get",
            path = "{region}/meshes/{meshName}/virtual-routers/{virtualRouterName}"
    )
    public VirtualRouterResponse meshesVirtualRoutersGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("virtualRouterName") final String virtualRouterName
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(DescribeVirtualRouterRequest.class, VirtualRouterResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVirtualRouterResult result = client.describeVirtualRouter(request.withMeshName(meshName).withVirtualRouterName(virtualRouterName));
            response.setVirtualRouter(result.getVirtualRouter());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualServices.list",
            path = "{region}/meshes/{meshName}/virtual-services"
    )
    public VirtualServicesResponse meshesVirtualServicesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListVirtualServicesRequest.class, VirtualServicesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVirtualServicesResult result = client.listVirtualServices(request.withMeshName(meshName).withNextToken(page));
            response.setItems(result.getVirtualServices());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualServices.get",
            path = "{region}/meshes/{meshName}/virtual-services/{virtualServiceName}"
    )
    public VirtualServiceResponse meshesVirtualServicesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("virtualServiceName") final String virtualServiceName
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(DescribeVirtualServiceRequest.class, VirtualServiceResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVirtualServiceResult result = client.describeVirtualService(request.withMeshName(meshName).withVirtualServiceName(virtualServiceName));
            response.setVirtualService(result.getVirtualService());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualNodes.list",
            path = "{region}/meshes/{meshName}/virtual-nodes"
    )
    public VirtualNodesResponse meshesVirtualNodesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListVirtualNodesRequest.class, VirtualNodesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVirtualNodesResult result = client.listVirtualNodes(request.withMeshName(meshName).withNextToken(page));
            response.setItems(result.getVirtualNodes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualNodes.get",
            path = "{region}/meshes/{meshName}/virtual-nodes/{virtualNodeName}"
    )
    public VirtualNodeResponse meshesVirtualNodesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("virtualNodeName") final String virtualNodeName
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(DescribeVirtualNodeRequest.class, VirtualNodeResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVirtualNodeResult result = client.describeVirtualNode(request.withMeshName(meshName).withVirtualNodeName(virtualNodeName));
            response.setVirtualNode(result.getVirtualNode());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualRouters.routes.list",
            path = "{region}/meshes/{meshName}/virtual-routers/{virtualRouterName}/routes"
    )
    public RoutesResponse meshesVirtualRoutersRoutesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("virtualRouterName") final String virtualRouterName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListRoutesRequest.class, RoutesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListRoutesResult result = client.listRoutes(request.withMeshName(meshName).withVirtualRouterName(virtualRouterName).withNextToken(page));
            response.setItems(result.getRoutes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "meshes.virtualRouters.routes.get",
            path = "{region}/meshes/{meshName}/virtual-routers/{virtualRouterName}/routes/{routeName}"
    )
    public RouteResponse meshesVirtualRoutersRoutesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("meshName") final String meshName,
            @Named("virtualRouterName") final String virtualRouterName,
            @Named("routeName") final String routeName
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(DescribeRouteRequest.class, RouteResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRouteResult result = client.describeRoute(request.withMeshName(meshName).withVirtualRouterName(virtualRouterName).withRouteName(routeName));
            response.setRoute(result.getRoute());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags"
    )
    public TagsListResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(ListTagsForResourceRequest.class, TagsListResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(request.withResourceArn(resourceArn).withNextToken(page));
            response.setTags(result.getTags());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse tagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagResourceRequest request
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(TagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, tagResourceRequest, response) -> {
            client.tagResource(tagResourceRequest.withResourceArn(request.getResourceArn()).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("resourceArn") final String resourceArn,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return AppMeshCaller.get(UntagResourceRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            client.untagResource(request.withResourceArn(resourceArn).withTagKeys(tagKeys));
        });
    }
}
