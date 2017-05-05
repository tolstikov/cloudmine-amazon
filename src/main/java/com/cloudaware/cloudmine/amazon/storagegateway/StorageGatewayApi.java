package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.AWSStorageGateway;
import com.amazonaws.services.storagegateway.model.DescribeBandwidthRateLimitRequest;
import com.amazonaws.services.storagegateway.model.DescribeBandwidthRateLimitResult;
import com.amazonaws.services.storagegateway.model.DescribeCachediSCSIVolumesRequest;
import com.amazonaws.services.storagegateway.model.DescribeCachediSCSIVolumesResult;
import com.amazonaws.services.storagegateway.model.DescribeStorediSCSIVolumesRequest;
import com.amazonaws.services.storagegateway.model.DescribeStorediSCSIVolumesResult;
import com.amazonaws.services.storagegateway.model.DescribeTapeArchivesRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapeArchivesResult;
import com.amazonaws.services.storagegateway.model.DescribeTapeRecoveryPointsRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapeRecoveryPointsResult;
import com.amazonaws.services.storagegateway.model.DescribeTapesRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapesResult;
import com.amazonaws.services.storagegateway.model.DescribeVTLDevicesRequest;
import com.amazonaws.services.storagegateway.model.DescribeVTLDevicesResult;
import com.amazonaws.services.storagegateway.model.Disk;
import com.amazonaws.services.storagegateway.model.ListGatewaysRequest;
import com.amazonaws.services.storagegateway.model.ListGatewaysResult;
import com.amazonaws.services.storagegateway.model.ListLocalDisksRequest;
import com.amazonaws.services.storagegateway.model.ListLocalDisksResult;
import com.amazonaws.services.storagegateway.model.ListVolumeRecoveryPointsRequest;
import com.amazonaws.services.storagegateway.model.ListVolumeRecoveryPointsResult;
import com.amazonaws.services.storagegateway.model.ListVolumesRequest;
import com.amazonaws.services.storagegateway.model.ListVolumesResult;
import com.amazonaws.services.storagegateway.model.VolumeRecoveryPointInfo;
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

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "storagegateway",
        canonicalName = "StorageGateway",
        title = "AWS Storage Gateway",
        description = "Hybrid Storage Integration",
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
public final class StorageGatewayApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.list",
            path = "{region}/gateways"
    )
    public GatewaysResponse gatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final ListGatewaysResult result = clientWrapper.getClient().listGateways(
                    new ListGatewaysRequest()
                            .withMarker(page)
            );
            return new GatewaysResponse(result.getGateways(), result.getMarker());
        } catch (Throwable t) {
            return new GatewaysResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.bandwidthRateLimit.get",
            path = "{region}/gateways/GATEWAY_ARN/bandwidth-rate-limit"
    )
    public BandwidthRateLimitResponse gatewaysBandwidthRateLimitGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeBandwidthRateLimitResult result = clientWrapper.getClient().describeBandwidthRateLimit(new DescribeBandwidthRateLimitRequest().withGatewayARN(gatewayArn));
            return new BandwidthRateLimitResponse(result.getAverageUploadRateLimitInBitsPerSec(), result.getAverageDownloadRateLimitInBitsPerSec());
        } catch (Throwable t) {
            return new BandwidthRateLimitResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.localDisks.list",
            path = "{region}/gateways/GATEWAY_ARN/local-disks"
    )
    public LocalDisksResponse gatewaysLocalDisksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final List<Disk> out = Lists.newArrayList();
            final ListLocalDisksResult result = clientWrapper.getClient().listLocalDisks(new ListLocalDisksRequest().withGatewayARN(gatewayArn));
            out.addAll(result.getDisks());
            return new LocalDisksResponse(out);
        } catch (Throwable t) {
            return new LocalDisksResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.volumes.list",
            path = "{region}/gateways/GATEWAY_ARN/volumes"
    )
    public VolumesResponse gatewaysVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final ListVolumesResult result = clientWrapper.getClient().listVolumes(
                    new ListVolumesRequest()
                            .withGatewayARN(gatewayArn)
                            .withMarker(page)
            );
            return new VolumesResponse(result.getVolumeInfos(), result.getMarker());
        } catch (Throwable t) {
            return new VolumesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "cachedVolumes.list",
            path = "{region}/cached-volumes"
    )
    public CachedVolumesResponse cachedVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("volumeArn") final List<String> volumeArns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeCachediSCSIVolumesResult result = clientWrapper.getClient().describeCachediSCSIVolumes(
                    new DescribeCachediSCSIVolumesRequest().withVolumeARNs(volumeArns)
            );
            return new CachedVolumesResponse(result.getCachediSCSIVolumes());
        } catch (Throwable t) {
            return new CachedVolumesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "storedVolumes.list",
            path = "{region}/stored-volumes"
    )
    public StoredVolumesResponse gatewaysStoredVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("volumeArn") final List<String> volumeArns
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeStorediSCSIVolumesResult result = clientWrapper.getClient().describeStorediSCSIVolumes(
                    new DescribeStorediSCSIVolumesRequest().withVolumeARNs(volumeArns)
            );
            return new StoredVolumesResponse(result.getStorediSCSIVolumes());
        } catch (Throwable t) {
            return new StoredVolumesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.volumeRecoveryPoints.list",
            path = "{region}/gateways/GATEWAY_ARN/volume-recovery-points"
    )
    public VolumeRecoveryPointsResponse gatewaysVolumeRecoveryPointsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final List<VolumeRecoveryPointInfo> out = Lists.newArrayList();
            final ListVolumeRecoveryPointsResult result = clientWrapper.getClient().listVolumeRecoveryPoints(
                    new ListVolumeRecoveryPointsRequest().withGatewayARN(gatewayArn));
            out.addAll(result.getVolumeRecoveryPointInfos());
            return new VolumeRecoveryPointsResponse(out);
        } catch (Throwable t) {
            return new VolumeRecoveryPointsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.tapes.list",
            path = "{region}/gateways/GATEWAY_ARN/tapes"
    )
    public TapesResponse gatewaysTapesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeTapesResult result = clientWrapper.getClient().describeTapes(
                    new DescribeTapesRequest()
                            .withGatewayARN(gatewayArn)
                            .withMarker(page)
            );
            return new TapesResponse(result.getTapes(), result.getMarker());
        } catch (Throwable t) {
            return new TapesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.tapeRecoveryPoints.list",
            path = "{region}/gateways/GATEWAY_ARN/tape-recovery-points"
    )
    public TapeRecoveryPointsResponse recoveryPointInfos(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeTapeRecoveryPointsResult result = clientWrapper.getClient().describeTapeRecoveryPoints(
                    new DescribeTapeRecoveryPointsRequest()
                            .withGatewayARN(gatewayArn)
                            .withMarker(page)
            );
            return new TapeRecoveryPointsResponse(result.getTapeRecoveryPointInfos(), result.getMarker());
        } catch (Throwable t) {
            return new TapeRecoveryPointsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tapeArchives.list",
            path = "{region}/tape-archives"
    )
    public TapeArchivesResponse tapeArchivesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeTapeArchivesResult result = clientWrapper.getClient().describeTapeArchives(
                    new DescribeTapeArchivesRequest()
                            .withMarker(page)
            );
            return new TapeArchivesResponse(result.getTapeArchives(), result.getMarker());
        } catch (Throwable t) {
            return new TapeArchivesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.vtlDevices.list",
            path = "{region}/gateways/GATEWAY_ARN/vtl-devices"
    )
    public VtlDevicesResponse gatewaysVtlDevicesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSStorageGateway> clientWrapper = new AmazonClientHelper(credentials).getStorageGateway(region)) {
            final DescribeVTLDevicesResult result = clientWrapper.getClient().describeVTLDevices(
                    new DescribeVTLDevicesRequest()
                            .withGatewayARN(gatewayArn)
                            .withMarker(page)
            );
            return new VtlDevicesResponse(result.getVTLDevices(), result.getMarker());
        } catch (Throwable t) {
            return new VtlDevicesResponse(AmazonResponse.parse(t));
        }
    }
}
