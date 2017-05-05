package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.model.AddTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeInstanceHealthRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeInstanceHealthResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerAttributesRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerPoliciesRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerPoliciesResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeTagsResult;
import com.amazonaws.services.elasticloadbalancing.model.Instance;
import com.amazonaws.services.elasticloadbalancing.model.RemoveTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.Tag;
import com.amazonaws.services.elasticloadbalancing.model.TagKeyOnly;
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
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "elb",
        canonicalName = "Elb",
        title = "Elastic Load Balancing",
        description = "High Scale Load Balancing",
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
public final class ElbApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.list",
            path = "{region}/load-balancers"
    )
    public LoadBalancersResponse loadBalancersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") @Nullable final List<String> loadBalancerNames,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final DescribeLoadBalancersResult result = clientWrapper.getClient().describeLoadBalancers(
                    new DescribeLoadBalancersRequest()
                            .withLoadBalancerNames(loadBalancerNames)
                            .withMarker(page)
            );
            return new LoadBalancersResponse(result.getLoadBalancerDescriptions(), result.getNextMarker());
        } catch (Throwable t) {
            return new LoadBalancersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.instanceHealth.list",
            path = "{region}/load-balancers/{loadBalancerName}/instance-health"
    )
    public InstanceStatesResponse loadBalancersInstanceHealthList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final String loadBalancerName,
            @Named("instanceId") final Collection<String> instanceIds
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final List<Instance> instances = Lists.newArrayList();
            for (final String instanceId : instanceIds) {
                instances.add(new Instance(instanceId));
            }
            final DescribeInstanceHealthResult result = clientWrapper.getClient().describeInstanceHealth(new DescribeInstanceHealthRequest(loadBalancerName).withInstances(instances));
            return new InstanceStatesResponse(result.getInstanceStates());
        } catch (Throwable t) {
            return new InstanceStatesResponse(AmazonResponse.parse(t));
        }

    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.attributes.get",
            path = "{region}/load-balancers/{loadBalancerName}/attributes"
    )
    public LoadBalancerAttributesResponse loadBalancersAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final String loadBalancerName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            return new LoadBalancerAttributesResponse(clientWrapper.getClient().describeLoadBalancerAttributes(
                    new DescribeLoadBalancerAttributesRequest().withLoadBalancerName(loadBalancerName)
            ).getLoadBalancerAttributes());
        } catch (Throwable t) {
            return new LoadBalancerAttributesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags"
    )
    public TagsResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final List<String> loadBalancerNames
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final DescribeTagsResult result = clientWrapper.getClient().describeTags(new DescribeTagsRequest().withLoadBalancerNames(loadBalancerNames));
            return new TagsResponse(result.getTagDescriptions());
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse tagsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final AddTagsRequest addTagsRequest = new AddTagsRequest();
            addTagsRequest.withLoadBalancerNames(request.getLoadBalancerNames());
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                tags.add(new Tag().withKey(key).withValue(request.getTags().get(key)));
            }
            addTagsRequest.withTags(tags);
            clientWrapper.getClient().addTags(addTagsRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final RemoveTagsRequest removeTagsRequest = new RemoveTagsRequest();
            removeTagsRequest.withLoadBalancerNames(request.getLoadBalancerNames());
            final List<TagKeyOnly> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                tags.add(new TagKeyOnly().withKey(key));
            }

            removeTagsRequest.withTags(tags);
            clientWrapper.getClient().removeTags(removeTagsRequest);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "{region}/policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") @Nullable final String loadBalancerName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonElasticLoadBalancing> clientWrapper = new AmazonClientHelper(credentials).getElb(region)) {
            final DescribeLoadBalancerPoliciesResult result = clientWrapper.getClient().describeLoadBalancerPolicies(new DescribeLoadBalancerPoliciesRequest().withLoadBalancerName(loadBalancerName));
            return new PoliciesResponse(result.getPolicyDescriptions());
        } catch (Throwable t) {
            return new PoliciesResponse(AmazonResponse.parse(t));
        }
    }

}
