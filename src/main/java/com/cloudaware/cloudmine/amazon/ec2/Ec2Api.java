package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteTagsRequest;
import com.amazonaws.services.ec2.model.DescribeAccountAttributesResult;
import com.amazonaws.services.ec2.model.DescribeAddressesResult;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsResult;
import com.amazonaws.services.ec2.model.DescribeFlowLogsRequest;
import com.amazonaws.services.ec2.model.DescribeFlowLogsResult;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsResult;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesResult;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsResult;
import com.amazonaws.services.ec2.model.DescribeReservedInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.DescribeVolumeStatusResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.DescribeVpnConnectionsResult;
import com.amazonaws.services.ec2.model.DescribeVpnGatewaysResult;
import com.amazonaws.services.ec2.model.ImageAttribute;
import com.amazonaws.services.ec2.model.InstanceAttribute;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
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
        name = "ec2",
        canonicalName = "Ec2",
        title = "Amazon EC2",
        description = "Virtual Servers in the Cloud",
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
public final class Ec2Api {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountAttributes.list",
            path = "{region}/account-attributes"
    )
    public AccountAttributesResponse accountAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeAccountAttributesResult result = clientWrapper.getClient().describeAccountAttributes();
            return new AccountAttributesResponse(result.getAccountAttributes());
        } catch (Throwable t) {
            return new AccountAttributesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dhcpOptions.list",
            path = "{region}/dhcp-options"
    )
    public DhcpOptionsResponse dhcpOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeDhcpOptionsResult result = clientWrapper.getClient().describeDhcpOptions();
            return new DhcpOptionsResponse(result.getDhcpOptions());
        } catch (Throwable t) {
            return new DhcpOptionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "elasticIps.list",
            path = "{region}/elastic-ips"
    )
    public ElasticIpsResponse elasticIpsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeAddressesResult result = clientWrapper.getClient().describeAddresses();
            return new ElasticIpsResponse(result.getAddresses());
        } catch (Throwable t) {
            return new ElasticIpsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "flowLogs.list",
            path = "{region}/flow-logs"
    )
    public FlowLogsResponse flowLogsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeFlowLogsResult result = clientWrapper.getClient().describeFlowLogs(
                    new DescribeFlowLogsRequest()
                            .withNextToken(page)
            );
            return new FlowLogsResponse(result.getFlowLogs(), result.getNextToken());
        } catch (Throwable t) {
            return new FlowLogsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.list",
            path = "{region}/images"
    )
    public ImagesResponse imagesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("owner") final String owner
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeImagesResult result = clientWrapper.getClient().describeImages(new DescribeImagesRequest().withOwners(owner));
            return new ImagesResponse(result.getImages());
        } catch (Throwable t) {
            return new ImagesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.attributes.get",
            path = "{region}/images/{imageId}/attributes/{attributeName}"
    )
    public ImageAttributeResponse imagesAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("imageId") final String imageId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final ImageAttribute imageAttribute = clientWrapper.getClient().describeImageAttribute(new DescribeImageAttributeRequest(imageId, attributeName)).getImageAttribute();
            return new ImageAttributeResponse(imageAttribute);
        } catch (Throwable t) {
            return new ImageAttributeResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.attributes.get",
            path = "{region}/instances/{instanceId}/attributes/{attributeName}"
    )
    public InstanceAttributeResponse instancesAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final InstanceAttribute instanceAttribute = clientWrapper.getClient().describeInstanceAttribute(new DescribeInstanceAttributeRequest(instanceId, attributeName)).getInstanceAttribute();
            return new InstanceAttributeResponse(instanceAttribute);
        } catch (Throwable t) {
            return new InstanceAttributeResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.list",
            path = "{region}/instances"
    )
    public InstancesResponse instancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") @Nullable final List<String> instanceIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeInstancesResult result = clientWrapper.getClient().describeInstances(
                    new DescribeInstancesRequest()
                            .withInstanceIds(instanceIds)
                            .withNextToken(page)
            );
            return new InstancesResponse(result.getReservations(), result.getNextToken());
        } catch (Throwable t) {
            return new InstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.run",
            path = "{region}/instances"
    )
    public ReservationResponse instancesRun(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final InstancesRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
            runInstancesRequest.setImageId(request.getImageId());
            runInstancesRequest.setMinCount(request.getMinCount());
            runInstancesRequest.setMaxCount(request.getMaxCount());
            runInstancesRequest.setKeyName(request.getKeyName());
            runInstancesRequest.setSecurityGroups(request.getSecurityGroups());
            runInstancesRequest.setSecurityGroupIds(request.getSecurityGroupIds());
            runInstancesRequest.setUserData(request.getUserData());
            runInstancesRequest.setInstanceType(request.getInstanceType());
            runInstancesRequest.setPlacement(request.getPlacement());
            runInstancesRequest.setKernelId(request.getKernelId());
            runInstancesRequest.setRamdiskId(request.getRamdiskId());
            runInstancesRequest.setBlockDeviceMappings(request.getBlockDeviceMappings());
            runInstancesRequest.setMonitoring(request.getMonitoring());
            runInstancesRequest.setSubnetId(request.getSubnetId());
            runInstancesRequest.setDisableApiTermination(request.getDisableApiTermination());
            runInstancesRequest.setInstanceInitiatedShutdownBehavior(request.getInstanceInitiatedShutdownBehavior());
            runInstancesRequest.setPrivateIpAddress(request.getPrivateIpAddress());
            runInstancesRequest.setIpv6Addresses(request.getIpv6Addresses());
            runInstancesRequest.setIpv6AddressCount(request.getIpv6AddressCount());
            runInstancesRequest.setClientToken(request.getClientToken());
            runInstancesRequest.setAdditionalInfo(request.getAdditionalInfo());
            runInstancesRequest.setNetworkInterfaces(request.getNetworkInterfaces());
            runInstancesRequest.setIamInstanceProfile(request.getIamInstanceProfile());
            runInstancesRequest.setEbsOptimized(request.getEbsOptimized());
            final RunInstancesResult result = clientWrapper.getClient().runInstances(runInstancesRequest);
            return new ReservationResponse(result.getReservation());
        } catch (Throwable t) {
            return new ReservationResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.start",
            path = "{region}/instances/{instanceId}/start"
    )
    public InstanceStateChangeResponse instancesStart(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            return new InstanceStateChangeResponse(
                    clientWrapper.getClient()
                            .startInstances(new StartInstancesRequest().withInstanceIds(instanceId))
                            .getStartingInstances()
                            .get(0)
            );
        } catch (Throwable t) {
            return new InstanceStateChangeResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.stop",
            path = "{region}/instances/{instanceId}/stop"
    )
    public InstanceStateChangeResponse instancesStop(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            return new InstanceStateChangeResponse(
                    clientWrapper.getClient()
                            .stopInstances(new StopInstancesRequest().withInstanceIds(instanceId))
                            .getStoppingInstances()
                            .get(0)
            );
        } catch (Throwable t) {
            return new InstanceStateChangeResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.reboot",
            path = "{region}/instances/{instanceId}/reboot"
    )
    public AmazonResponse instancesReboot(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            clientWrapper.getClient().rebootInstances(new RebootInstancesRequest().withInstanceIds(instanceId));
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instanceStatuses.list",
            path = "{region}/instance-statuses"
    )
    public InstanceStatusesResponse instanceStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeInstanceStatusResult result = clientWrapper.getClient().describeInstanceStatus(
                    new DescribeInstanceStatusRequest()
                            .withNextToken(page)
            );
            return new InstanceStatusesResponse(result.getInstanceStatuses(), result.getNextToken());
        } catch (Throwable t) {
            return new InstanceStatusesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "internetGateways.list",
            path = "{region}/internet-gateways"
    )
    public InternetGatewaysResponse internetGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeInternetGatewaysResult result = clientWrapper.getClient().describeInternetGateways();
            return new InternetGatewaysResponse(result.getInternetGateways());
        } catch (Throwable t) {
            return new InternetGatewaysResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keyPairs.list",
            path = "{region}/key-pairs"
    )
    public KeyPairsResponse keyPairsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyName") @Nullable final List<String> keyNames
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            return new KeyPairsResponse(
                    clientWrapper.getClient()
                            .describeKeyPairs(new DescribeKeyPairsRequest().withKeyNames(keyNames))
                            .getKeyPairs()
            );
        } catch (Throwable t) {
            return new KeyPairsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "keyPairs.create",
            path = "{region}/key-pairs"
    )
    public KeyPairResponse keyPairsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyName") final String keyName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            return new KeyPairResponse(clientWrapper.getClient().createKeyPair(new CreateKeyPairRequest().withKeyName(keyName)).getKeyPair());
        } catch (Throwable t) {
            return new KeyPairResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "networkAcls.list",
            path = "{region}/network-acls"
    )
    public NetworkAclsResponse networkAclsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeNetworkAclsResult result = clientWrapper.getClient().describeNetworkAcls();
            return new NetworkAclsResponse(result.getNetworkAcls());
        } catch (Throwable t) {
            return new NetworkAclsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "networkInterfaces.list",
            path = "{region}/network-interfaces"
    )
    public NetworkInterfacesResponse networkInterfacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeNetworkInterfacesResult result = clientWrapper.getClient().describeNetworkInterfaces();
            return new NetworkInterfacesResponse(result.getNetworkInterfaces());
        } catch (Throwable t) {
            return new NetworkInterfacesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "placementGroups.list",
            path = "{region}/placement-groups"
    )
    public PlacementGroupsResponse placementGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribePlacementGroupsResult result = clientWrapper.getClient().describePlacementGroups();
            return new PlacementGroupsResponse(result.getPlacementGroups());
        } catch (Throwable t) {
            return new PlacementGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedInstances.list",
            path = "{region}/reserved-instances"
    )
    public ReservedInstancesResponse reservedInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeReservedInstancesResult result = clientWrapper.getClient().describeReservedInstances();
            return new ReservedInstancesResponse(result.getReservedInstances());
        } catch (Throwable t) {
            return new ReservedInstancesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "routeTables.list",
            path = "{region}/route-tables"
    )
    public RouteTablesResponse routeTablesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeRouteTablesResult result = clientWrapper.getClient().describeRouteTables();
            return new RouteTablesResponse(result.getRouteTables());
        } catch (Throwable t) {
            return new RouteTablesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public SecurityGroupsResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeSecurityGroupsResult result = clientWrapper.getClient().describeSecurityGroups();
            return new SecurityGroupsResponse(result.getSecurityGroups());
        } catch (Throwable t) {
            return new SecurityGroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.list",
            path = "{region}/snapshots"
    )
    public SnapshotsResponse ownedBySelf(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("owner") final String owner,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeSnapshotsResult result = clientWrapper.getClient().describeSnapshots(
                    new DescribeSnapshotsRequest()
                            .withOwnerIds(owner)
                            .withNextToken(page)
            );
            return new SnapshotsResponse(result.getSnapshots(), result.getNextToken());
        } catch (Throwable t) {
            return new SnapshotsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subnets.list",
            path = "{region}/subnets"
    )
    public SubnetsResponse subnetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeSubnetsResult result = clientWrapper.getClient().describeSubnets();
            return new SubnetsResponse(result.getSubnets());
        } catch (Throwable t) {
            return new SubnetsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.attributes.get",
            path = "{region}/snapshots/{snapshotId}/attributes/{attributeName}"
    )
    public SnapshotAttributeResponse snapshotsAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("snapshotId") final String snapshotId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeSnapshotAttributeResult result = clientWrapper.getClient().describeSnapshotAttribute(new DescribeSnapshotAttributeRequest(snapshotId, attributeName));
            return new SnapshotAttributeResponse(result.getCreateVolumePermissions());
        } catch (Throwable t) {
            return new SnapshotAttributeResponse(AmazonResponse.parse(t));
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
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final CreateTagsRequest req = new CreateTagsRequest();
            req.withResources(tagsRequest.getResources());
            req.withTags(tagsRequest.getTags());
            clientWrapper.getClient().createTags(req);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/delete"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DeleteTagsRequest req = new DeleteTagsRequest();
            req.withResources(tagsRequest.getResources());
            req.withTags(tagsRequest.getTags());
            clientWrapper.getClient().deleteTags(req);
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "volumes.list",
            path = "{region}/volumes"
    )
    public VolumesResponse volumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeVolumesResult result = clientWrapper.getClient().describeVolumes(
                    new DescribeVolumesRequest()
                            .withNextToken(page)
            );
            return new VolumesResponse(result.getVolumes(), result.getNextToken());
        } catch (Throwable t) {
            return new VolumesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "volumeStatuses.list",
            path = "{region}/volume-statuses"
    )
    public VolumeStatusesResponse volumeStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeVolumeStatusResult result = clientWrapper.getClient().describeVolumeStatus();
            return new VolumeStatusesResponse(result.getVolumeStatuses());
        } catch (Throwable t) {
            return new VolumeStatusesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpcs.list",
            path = "{region}/vpcs"
    )
    public VpcsResponse vpcsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeVpcsResult result = clientWrapper.getClient().describeVpcs();
            return new VpcsResponse(result.getVpcs());
        } catch (Throwable t) {
            return new VpcsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpnConnections.list",
            path = "{region}/vpn-connections"
    )
    public VpnConnectionsResponse vpnConnectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeVpnConnectionsResult result = clientWrapper.getClient().describeVpnConnections();
            return new VpnConnectionsResponse(result.getVpnConnections());
        } catch (Throwable t) {
            return new VpnConnectionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpnGateways.list",
            path = "{region}/vpn-gateways"
    )
    public VpnGatewaysResponse vpnGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonEC2> clientWrapper = new AmazonClientHelper(credentials).getEc2(region)) {
            final DescribeVpnGatewaysResult result = clientWrapper.getClient().describeVpnGateways();
            return new VpnGatewaysResponse(result.getVpnGateways());
        } catch (Throwable t) {
            return new VpnGatewaysResponse(AmazonResponse.parse(t));
        }
    }
}
