package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.AWSOrganizations;
import com.amazonaws.services.organizations.model.CreateAccountRequest;
import com.amazonaws.services.organizations.model.CreateAccountResult;
import com.amazonaws.services.organizations.model.DescribeCreateAccountStatusRequest;
import com.amazonaws.services.organizations.model.DescribeCreateAccountStatusResult;
import com.amazonaws.services.organizations.model.DescribeOrganizationRequest;
import com.amazonaws.services.organizations.model.ListAccountsForParentRequest;
import com.amazonaws.services.organizations.model.ListAccountsForParentResult;
import com.amazonaws.services.organizations.model.ListAccountsRequest;
import com.amazonaws.services.organizations.model.ListAccountsResult;
import com.amazonaws.services.organizations.model.ListOrganizationalUnitsForParentRequest;
import com.amazonaws.services.organizations.model.ListOrganizationalUnitsForParentResult;
import com.amazonaws.services.organizations.model.ListPoliciesForTargetRequest;
import com.amazonaws.services.organizations.model.ListPoliciesForTargetResult;
import com.amazonaws.services.organizations.model.ListPoliciesRequest;
import com.amazonaws.services.organizations.model.ListPoliciesResult;
import com.amazonaws.services.organizations.model.ListRootsRequest;
import com.amazonaws.services.organizations.model.ListRootsResult;
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

@Api(
        name = "organizations",
        canonicalName = "Organizations",
        title = "AWS Organizations",
        description = "Policy-based management for multiple AWS accounts",
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
public final class OrganizationsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "organization.get",
            path = "organization"
    )
    public OrganizationResponse organizationGet(
            @Named("credentials") final String credentials
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            return new OrganizationResponse(
                    clientWrapper.getClient()
                            .describeOrganization(new DescribeOrganizationRequest())
                            .getOrganization()
            );
        } catch (Throwable t) {
            return new OrganizationResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roots.list",
            path = "roots"
    )
    public RootsResponse rootslist(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListRootsResult result = clientWrapper.getClient().listRoots(
                    new ListRootsRequest()
                            .withNextToken(page)
            );
            return new RootsResponse(result.getRoots(), result.getNextToken());
        } catch (Throwable t) {
            return new RootsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accounts.list",
            path = "accounts"
    )
    public AccountsResponse accountsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListAccountsResult result = clientWrapper.getClient().listAccounts(
                    new ListAccountsRequest()
                            .withNextToken(page)
            );
            return new AccountsResponse(result.getAccounts(), result.getNextToken());
        } catch (Throwable t) {
            return new AccountsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "accounts.createRequests.insert",
            path = "accounts/create-requests"
    )
    public AccountCreateResponse accountsCreate(
            @Named("credentials") final String credentials,
            final AccountRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final CreateAccountResult result = clientWrapper.getClient().createAccount(
                    new CreateAccountRequest()
                            .withAccountName(request.getAccountName())
                            .withEmail(request.getEmail())
                            .withRoleName(request.getRoleName())
                            .withIamUserAccessToBilling(request.getIamUserAccessToBilling())

            );
            return new AccountCreateResponse(result.getCreateAccountStatus());
        } catch (Throwable t) {
            return new AccountCreateResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accounts.createRequests.get",
            path = "accounts/create-requests/{requestId}"
    )
    public AccountCreateResponse accountsCreateStatus(
            @Named("credentials") final String credentials,
            @Named("requestId") final String requestId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final DescribeCreateAccountStatusResult result = clientWrapper.getClient().describeCreateAccountStatus(new DescribeCreateAccountStatusRequest()
                    .withCreateAccountRequestId(requestId)
            );
            return new AccountCreateResponse(result.getCreateAccountStatus());
        } catch (Throwable t) {
            return new AccountCreateResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parents.accounts.list",
            path = "parents/{parentId}/accounts"
    )
    public AccountsResponse parentsAccountsList(
            @Named("credentials") final String credentials,
            @Named("parentId") final String parentId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListAccountsForParentResult result = clientWrapper.getClient().listAccountsForParent(
                    new ListAccountsForParentRequest()
                            .withParentId(parentId)
                            .withNextToken(page)
            );
            return new AccountsResponse(result.getAccounts(), result.getNextToken());
        } catch (Throwable t) {
            return new AccountsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("filter") final String filter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListPoliciesResult result = clientWrapper.getClient().listPolicies(
                    new ListPoliciesRequest()
                            .withFilter(filter)
                            .withNextToken(page)
            );
            return new PoliciesResponse(result.getPolicies(), result.getNextToken());
        } catch (Throwable t) {
            return new PoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targets.policies.list",
            path = "targets/{targetId}/policies"
    )
    public PoliciesResponse targetsPoliciesList(
            @Named("credentials") final String credentials,
            @Named("targetId") final String targetId,
            @Named("filter") final String filter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListPoliciesForTargetResult result = clientWrapper.getClient().listPoliciesForTarget(
                    new ListPoliciesForTargetRequest()
                            .withTargetId(targetId)
                            .withFilter(filter)
                            .withNextToken(page)
            );
            return new PoliciesResponse(result.getPolicies(), result.getNextToken());
        } catch (Throwable t) {
            return new PoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parents.organizationalUnits.list",
            path = "parents/{parentId}/organizational-units"
    )
    public OrganizationalUnitsResponse parentsOrganizationalUnitsList(
            @Named("credentials") final String credentials,
            @Named("parentId") final String parentId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSOrganizations> clientWrapper = new AmazonClientHelper(credentials).getOrganizations()) {
            final ListOrganizationalUnitsForParentResult result = clientWrapper.getClient().listOrganizationalUnitsForParent(
                    new ListOrganizationalUnitsForParentRequest()
                            .withParentId(parentId)
                            .withNextToken(page)
            );
            return new OrganizationalUnitsResponse(result.getOrganizationalUnits(), result.getNextToken());
        } catch (Throwable t) {
            return new OrganizationalUnitsResponse(AmazonResponse.parse(t));
        }
    }
}
