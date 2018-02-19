package com.cloudaware.cloudmine.amazon.guardduty;

import com.amazonaws.services.guardduty.model.GetDetectorRequest;
import com.amazonaws.services.guardduty.model.GetDetectorResult;
import com.amazonaws.services.guardduty.model.GetFindingsRequest;
import com.amazonaws.services.guardduty.model.GetFindingsResult;
import com.amazonaws.services.guardduty.model.GetFindingsStatisticsRequest;
import com.amazonaws.services.guardduty.model.GetFindingsStatisticsResult;
import com.amazonaws.services.guardduty.model.GetIPSetRequest;
import com.amazonaws.services.guardduty.model.GetIPSetResult;
import com.amazonaws.services.guardduty.model.GetMasterAccountRequest;
import com.amazonaws.services.guardduty.model.GetMasterAccountResult;
import com.amazonaws.services.guardduty.model.GetThreatIntelSetRequest;
import com.amazonaws.services.guardduty.model.GetThreatIntelSetResult;
import com.amazonaws.services.guardduty.model.ListDetectorsRequest;
import com.amazonaws.services.guardduty.model.ListDetectorsResult;
import com.amazonaws.services.guardduty.model.ListFindingsRequest;
import com.amazonaws.services.guardduty.model.ListFindingsResult;
import com.amazonaws.services.guardduty.model.ListIPSetsRequest;
import com.amazonaws.services.guardduty.model.ListIPSetsResult;
import com.amazonaws.services.guardduty.model.ListInvitationsRequest;
import com.amazonaws.services.guardduty.model.ListInvitationsResult;
import com.amazonaws.services.guardduty.model.ListMembersRequest;
import com.amazonaws.services.guardduty.model.ListMembersResult;
import com.amazonaws.services.guardduty.model.ListThreatIntelSetsRequest;
import com.amazonaws.services.guardduty.model.ListThreatIntelSetsResult;
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
        name = "guardduty",
        canonicalName = "GuardDuty",
        title = "Amazon GuardDuty",
        description = "Intelligent threat detection and continuous monitoring to protect your AWS accounts and workloads",
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
public final class GuardDutyApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectorIds.list",
            path = "{region}/detector-ids"
    )
    public DetectorIdsResponse detectorIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListDetectorsRequest.class, DetectorIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDetectorsResult result = client.listDetectors(request.withNextToken(page));
            response.setDetectorIds(result.getDetectorIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.get",
            path = "{region}/detectors/{detectorId}"
    )
    public DetectorResponse detectorsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetDetectorRequest.class, DetectorResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDetectorResult result = client.getDetector(request.withDetectorId(detectorId));
            response.setCreatedAt(result.getCreatedAt());
            response.setServiceRole(result.getServiceRole());
            response.setStatus(result.getStatus());
            response.setUpdatedAt(result.getUpdatedAt());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.findingIds.list",
            path = "{region}/detectors/{detectorId}/finding-ids"
    )
    public FindingIdsResponse detectorsFindingIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListFindingsRequest.class, FindingIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListFindingsResult result = client.listFindings(
                    request
                            .withDetectorId(detectorId)
                            .withNextToken(page)
            );
            response.setFindingIds(result.getFindingIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.findings.list",
            path = "{region}/detectors/{detectorId}/findings"
    )
    public FindingsResponse detectorsFindingsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("findingId") final List<String> findingIds
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetFindingsRequest.class, FindingsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetFindingsResult result = client.getFindings(
                    request
                            .withDetectorId(detectorId)
                            .withFindingIds(findingIds)
            );
            response.setFindings(result.getFindings());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.findingsStatistics.get",
            path = "{region}/detectors/{detectorId}/finding-statistics"
    )
    public FindingsStatisticsResponse detectorsFindingsStatisticsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("findingStatisticType") final List<String> findingStatisticTypes
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetFindingsStatisticsRequest.class, FindingsStatisticsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetFindingsStatisticsResult result = client.getFindingsStatistics(
                    request
                            .withDetectorId(detectorId)
                            .withFindingStatisticTypes(findingStatisticTypes)
            );
            response.setFindingStatistics(result.getFindingStatistics());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "invitations.list",
            path = "{region}/invitations"
    )
    public InvitationsResponse invitationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListInvitationsRequest.class, InvitationsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListInvitationsResult result = client.listInvitations(request.withNextToken(page));
            response.setInvitations(result.getInvitations());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.members.list",
            path = "{region}/detectors/{detectorId}/members"
    )
    public MembersResponse detectorsMembersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListMembersRequest.class, MembersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListMembersResult result = client.listMembers(
                    request
                            .withDetectorId(detectorId)
                            .withNextToken(page)
            );
            response.setMembers(result.getMembers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.masterAccount.get",
            path = "{region}/detectors/{detectorId}/master-account"
    )
    public MasterAccountResponse detectorsMasterAccountGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetMasterAccountRequest.class, MasterAccountResponse.class, credentials, region).execute((client, request, response) -> {
            final GetMasterAccountResult result = client.getMasterAccount(request.withDetectorId(detectorId));
            response.setMasterAccount(result.getMaster());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.ipSetIds.list",
            path = "{region}/detectors/{detectorId}/ip-set-ids"
    )
    public SetIdsResponse detectorsIpSetIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListIPSetsRequest.class, SetIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListIPSetsResult result = client.listIPSets(
                    request
                            .withDetectorId(detectorId)
                            .withNextToken(page)
            );
            response.setSetIds(result.getIpSetIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.ipSets.get",
            path = "{region}/detectors/{detectorId}/ip-sets/{ipSetId}"
    )
    public SetResponse detectorsIpSetsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("ipSetId") final String ipSetId
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetIPSetRequest.class, SetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIPSetResult result = client.getIPSet(
                    request
                            .withDetectorId(detectorId)
                            .withIpSetId(ipSetId)
            );
            response.setFormat(result.getFormat());
            response.setLocation(result.getLocation());
            response.setName(result.getName());
            response.setStatus(result.getStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.threatIntelSetIds.list",
            path = "{region}/detectors/{detectorId}/threat-intel-set-ids"
    )
    public SetIdsResponse detectorsThreatIntelSetIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(ListThreatIntelSetsRequest.class, SetIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListThreatIntelSetsResult result = client.listThreatIntelSets(
                    request
                            .withDetectorId(detectorId)
                            .withNextToken(page)
            );
            response.setSetIds(result.getThreatIntelSetIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "detectors.threatIntelSets.get",
            path = "{region}/detectors/{detectorId}/threat-intel-sets/{threatIntelSetId}"
    )
    public SetResponse detectorsThreatIntelSetsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("detectorId") final String detectorId,
            @Named("threatIntelSetId") final String threatIntelSetId
    ) throws AmazonUnparsedException {
        return GuardDutyCaller.get(GetThreatIntelSetRequest.class, SetResponse.class, credentials, region).execute((client, request, response) -> {
            final GetThreatIntelSetResult result = client.getThreatIntelSet(
                    request
                            .withDetectorId(detectorId)
                            .withThreatIntelSetId(threatIntelSetId)
            );
            response.setFormat(result.getFormat());
            response.setLocation(result.getLocation());
            response.setName(result.getName());
            response.setStatus(result.getStatus());
        });
    }
}
