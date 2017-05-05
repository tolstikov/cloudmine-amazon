package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.services.directconnect.AmazonDirectConnect;
import com.amazonaws.services.directconnect.model.DescribeConnectionsOnInterconnectRequest;
import com.amazonaws.services.directconnect.model.DescribeConnectionsOnInterconnectResult;
import com.amazonaws.services.directconnect.model.DescribeConnectionsRequest;
import com.amazonaws.services.directconnect.model.DescribeConnectionsResult;
import com.amazonaws.services.directconnect.model.DescribeInterconnectsRequest;
import com.amazonaws.services.directconnect.model.DescribeInterconnectsResult;
import com.amazonaws.services.directconnect.model.DescribeVirtualInterfacesRequest;
import com.amazonaws.services.directconnect.model.DescribeVirtualInterfacesResult;
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

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "directconnect",
        canonicalName = "DirectConnect",
        title = "AWS Direct Connect",
        description = "Dedicated Network Connection to AWS",
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
public final class DirectConnectApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "connections.list",
            path = "{region}/connections"
    )
    public ConnectionsResponse connectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("connectionId") @Nullable final String connectionId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonDirectConnect> clientWrapper = new AmazonClientHelper(credentials).getDirectConnect(region)) {
            final DescribeConnectionsResult result = clientWrapper.getClient()
                    .describeConnections(new DescribeConnectionsRequest().withConnectionId(connectionId));
            return new ConnectionsResponse(result.getConnections());
        } catch (Throwable t) {
            return new ConnectionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "interconnects.list",
            path = "{region}/interconnects"
    )
    public InterconnectsResponse interconnectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("interconnectId") @Nullable final String interconnectId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonDirectConnect> clientWrapper = new AmazonClientHelper(credentials).getDirectConnect(region)) {
            final DescribeInterconnectsResult result = clientWrapper.getClient()
                    .describeInterconnects(new DescribeInterconnectsRequest().withInterconnectId(interconnectId));
            return new InterconnectsResponse(result.getInterconnects());
        } catch (Throwable t) {
            return new InterconnectsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "interconnects.connections.list",
            path = "{region}/interconnects/{interconnectId}/connections"
    )
    public ConnectionsResponse interconnectsLonnectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("interconnectId") final String interconnectId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonDirectConnect> clientWrapper = new AmazonClientHelper(credentials).getDirectConnect(region)) {
            final DescribeConnectionsOnInterconnectResult result = clientWrapper.getClient()
                    .describeConnectionsOnInterconnect(new DescribeConnectionsOnInterconnectRequest().withInterconnectId(interconnectId));
            return new ConnectionsResponse(result.getConnections());
        } catch (Throwable t) {
            return new ConnectionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "virtualInterfaces.list",
            path = "{region}/virtual-interfaces"
    )
    public VirtualInterfacesResponse virtualInterfacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("connectionId") @Nullable final String connectionId,
            @Named("virtualInterfaceId") @Nullable final String virtualInterfaceId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonDirectConnect> clientWrapper = new AmazonClientHelper(credentials).getDirectConnect(region)) {
            final DescribeVirtualInterfacesResult result = clientWrapper.getClient()
                    .describeVirtualInterfaces(
                            new DescribeVirtualInterfacesRequest()
                                    .withConnectionId(connectionId)
                                    .withVirtualInterfaceId(virtualInterfaceId)
                    );
            return new VirtualInterfacesResponse(result.getVirtualInterfaces());
        } catch (Throwable t) {
            return new VirtualInterfacesResponse(AmazonResponse.parse(t));
        }
    }
}
