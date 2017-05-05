package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.model.AccessKeyLastUsed;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedRequest;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedResult;
import com.amazonaws.services.identitymanagement.model.GetAccountPasswordPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.GetPolicyVersionRequest;
import com.amazonaws.services.identitymanagement.model.GetPolicyVersionResult;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyResult;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderRequest;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderResult;
import com.amazonaws.services.identitymanagement.model.GetServerCertificateRequest;
import com.amazonaws.services.identitymanagement.model.GetServerCertificateResult;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetUserRequest;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedGroupPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedRolePoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedRolePoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserResult;
import com.amazonaws.services.identitymanagement.model.ListGroupsRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsResult;
import com.amazonaws.services.identitymanagement.model.ListMFADevicesRequest;
import com.amazonaws.services.identitymanagement.model.ListMFADevicesResult;
import com.amazonaws.services.identitymanagement.model.ListPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListPolicyVersionsRequest;
import com.amazonaws.services.identitymanagement.model.ListPolicyVersionsResult;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListRolesRequest;
import com.amazonaws.services.identitymanagement.model.ListRolesResult;
import com.amazonaws.services.identitymanagement.model.ListSAMLProvidersRequest;
import com.amazonaws.services.identitymanagement.model.ListSAMLProvidersResult;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesResult;
import com.amazonaws.services.identitymanagement.model.ListSigningCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListSigningCertificatesResult;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.ListVirtualMFADevicesRequest;
import com.amazonaws.services.identitymanagement.model.ListVirtualMFADevicesResult;
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

import java.io.UnsupportedEncodingException;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "iam",
        canonicalName = "Iam",
        title = "AWS Identity & Access Management (IAM)",
        description = "Manage User Access and Encryption Keys",
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
public final class IamApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.list",
            path = "{partition}/users"
    )
    public UsersResponse usersList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListUsersResult result = clientWrapper.getClient().listUsers(
                    new ListUsersRequest()
                            .withMarker(page)
            );
            return new UsersResponse(result.getUsers(), result.getMarker());
        } catch (Throwable t) {
            return new UsersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.self",
            path = "{partition}/users/self"
    )
    public UserResponse usersSelf(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetUserResult result = clientWrapper.getClient().getUser();
            return new UserResponse(result.getUser());
        } catch (Throwable t) {
            return new UserResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.get",
            path = "{partition}/users/{userName}"
    )
    public UserResponse usersGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetUserResult result = clientWrapper.getClient().getUser(new GetUserRequest().withUserName(userName));
            return new UserResponse(result.getUser());
        } catch (Throwable t) {
            return new UserResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.groups.list",
            path = "{partition}/users/{userName}/groups"
    )
    public GroupsResponse usersGroupsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListGroupsForUserResult result = clientWrapper.getClient().listGroupsForUser(
                    new ListGroupsForUserRequest(userName)
                            .withMarker(page)
            );
            return new GroupsResponse(result.getGroups(), result.getMarker());
        } catch (Throwable t) {
            return new GroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.accessKeys.list",
            path = "{partition}/users/{userName}/access-keys"
    )
    public AccessKeysResponse usersAccessKeysList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListAccessKeysResult result = clientWrapper.getClient().listAccessKeys(
                    new ListAccessKeysRequest()
                            .withUserName(userName)
                            .withMarker(page)
            );
            return new AccessKeysResponse(result.getAccessKeyMetadata(), result.getMarker());
        } catch (Throwable t) {
            return new AccessKeysResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.list",
            path = "{partition}/groups"
    )
    public GroupsResponse groupsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListGroupsResult result = clientWrapper.getClient().listGroups(
                    new ListGroupsRequest()
                            .withMarker(page)
            );
            return new GroupsResponse(result.getGroups(), result.getMarker());
        } catch (Throwable t) {
            return new GroupsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.list",
            path = "{partition}/roles"
    )
    public RolesResponse rolesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListRolesResult result = clientWrapper.getClient().listRoles(
                    new ListRolesRequest()
                            .withMarker(page)
            );
            return new RolesResponse(result.getRoles(), result.getMarker());
        } catch (Throwable t) {
            return new RolesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accessKeys.lastUsed",
            path = "{partition}/access-keys/{accessKeyId}"
    )
    public AccessKeyLastUsedResponse accessKeyLastUsed(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("accessKeyId") final String accessKeyId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetAccessKeyLastUsedResult result = clientWrapper.getClient().getAccessKeyLastUsed(new GetAccessKeyLastUsedRequest().withAccessKeyId(accessKeyId));
            return new AccessKeyLastUsedResponse(
                    result != null && result.getAccessKeyLastUsed() != null
                            ? result.getAccessKeyLastUsed()
                            : (AccessKeyLastUsed) null
            );
        } catch (Throwable t) {
            return new AccessKeyLastUsedResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "users.accessKeys.create",
            path = "{partition}/users/{userName}"
    )
    public AccessKeyResponse usersAccessKeysCreate(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            return new AccessKeyResponse(
                    clientWrapper.getClient()
                            .createAccessKey(new CreateAccessKeyRequest().withUserName(userName))
                            .getAccessKey()
            );
        } catch (Throwable t) {
            return new AccessKeyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "serverCertificates.list",
            path = "{partition}/server-certificates"
    )
    public ServerCertificatesResponse serverCertificatesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListServerCertificatesResult result = clientWrapper.getClient().listServerCertificates(
                    new ListServerCertificatesRequest()
                            .withMarker(page)
            );
            return new ServerCertificatesResponse(result.getServerCertificateMetadataList(), result.getMarker());
        } catch (Throwable t) {
            return new ServerCertificatesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "serverCertificates.get",
            path = "{partition}/server-certificates/{serverCertificateName}"
    )
    public ServerCertificateResponse serverCertificatesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("serverCertificateName") final String serverCertificateName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetServerCertificateResult result = clientWrapper.getClient()
                    .getServerCertificate(new GetServerCertificateRequest().withServerCertificateName(serverCertificateName));
            return new ServerCertificateResponse(result.getServerCertificate());
        } catch (Throwable t) {
            return new ServerCertificateResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.signingCertificates.list",
            path = "{partition}/users/{userName}/signing-certificates"
    )
    public SigningCertificatesResponse usersSigningCertificatesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListSigningCertificatesResult result = clientWrapper.getClient().listSigningCertificates(
                    new ListSigningCertificatesRequest()
                            .withUserName(userName)
                            .withMarker(page)
            );
            return new SigningCertificatesResponse(result.getCertificates(), result.getMarker());
        } catch (Throwable t) {
            return new SigningCertificatesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.policies.list",
            path = "{partition}/groups/{groupName}/policies"
    )
    public PolicyNamesResponse groupsPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListGroupPoliciesResult result = clientWrapper.getClient().listGroupPolicies(
                    new ListGroupPoliciesRequest(groupName)
                            .withMarker(page)
            );
            return new PolicyNamesResponse(result.getPolicyNames(), result.getMarker());
        } catch (Throwable t) {
            return new PolicyNamesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.policies.get",
            path = "{partition}/groups/{groupName}/policies/{policyName}"
    )
    public PolicyResponse groupsPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetGroupPolicyResult result = clientWrapper.getClient()
                    .getGroupPolicy(new GetGroupPolicyRequest(groupName, policyName));
            return new PolicyResponse(result.getPolicyDocument());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.attachedPolicies.list",
            path = "{partition}/groups/{groupName}/attached-policies"
    )
    public AttachedPoliciesResponse groupsAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListAttachedGroupPoliciesResult result = clientWrapper.getClient().listAttachedGroupPolicies(
                    new ListAttachedGroupPoliciesRequest()
                            .withGroupName(groupName)
                            .withMarker(page)
            );
            return new AttachedPoliciesResponse(result.getAttachedPolicies(), result.getMarker());
        } catch (Throwable t) {
            return new AttachedPoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.loginProfile.get",
            path = "{partition}/users/{userName}/login-profile"
    )
    public LoginProfileResponse usersLoginProfileGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetLoginProfileResult result = clientWrapper.getClient()
                    .getLoginProfile(new GetLoginProfileRequest(userName));
            return new LoginProfileResponse(result.getLoginProfile());
        } catch (Throwable t) {
            return new LoginProfileResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.mfaDevices.list",
            path = "{partition}/users/{userName}/mfa-devices"
    )
    public MfaDevicesResponse usersMfaDevicesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListMFADevicesResult result = clientWrapper.getClient().listMFADevices(
                    new ListMFADevicesRequest(userName)
                            .withMarker(page)
            );
            return new MfaDevicesResponse(result.getMFADevices(), result.getMarker());
        } catch (Throwable t) {
            return new MfaDevicesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "passwordPolicy.get",
            path = "{partition}/password-policy"
    )
    public PasswordPolicyResponse passwordPolicyGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetAccountPasswordPolicyResult result = clientWrapper.getClient()
                    .getAccountPasswordPolicy();
            return new PasswordPolicyResponse(result.getPasswordPolicy());
        } catch (Throwable t) {
            return new PasswordPolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "{partition}/policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("scope") @Nullable final String scope,
            @Named("onlyAttached") @Nullable final Boolean onlyAttached,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListPoliciesResult result = clientWrapper.getClient().listPolicies(
                    new ListPoliciesRequest()
                            .withScope(scope)
                            .withOnlyAttached(onlyAttached)
                            .withMarker(page)
            );
            return new PoliciesResponse(result.getPolicies(), result.getMarker());
        } catch (Throwable t) {
            return new PoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.versions.list",
            path = "{partition}/policies/POLICY_ARN/versions"
    )
    public PolicyVersionsResponse policiesVersionsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("policyArn") final String policyArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListPolicyVersionsResult result = clientWrapper.getClient().listPolicyVersions(
                    new ListPolicyVersionsRequest()
                            .withPolicyArn(policyArn)
                            .withMarker(page)
            );
            return new PolicyVersionsResponse(result.getVersions(), result.getMarker());
        } catch (Throwable t) {
            return new PolicyVersionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.versions.get",
            path = "{partition}/policies/POLICY_ARN/versions/{versionId}"
    )
    public PolicyVersionResponse policiesVersionsGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("policyArn") final String policyArn,
            @Named("versionId") final String versionId
    ) throws UnsupportedEncodingException, AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetPolicyVersionResult result = clientWrapper.getClient()
                    .getPolicyVersion(new GetPolicyVersionRequest().withPolicyArn(policyArn).withVersionId(versionId));
            return new PolicyVersionResponse(result.getPolicyVersion());
        } catch (Throwable t) {
            return new PolicyVersionResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.policies.list",
            path = "{partition}/roles/{roleName}/policies"
    )
    public PolicyNamesResponse rolesPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListRolePoliciesResult result = clientWrapper.getClient().listRolePolicies(
                    new ListRolePoliciesRequest()
                            .withRoleName(roleName)
                            .withMarker(page)
            );
            return new PolicyNamesResponse(result.getPolicyNames(), result.getMarker());
        } catch (Throwable t) {
            return new PolicyNamesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.policies.get",
            path = "{partition}/roles/{roleName}/policies/{policyName}"
    )
    public PolicyResponse rolesPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetRolePolicyResult result = clientWrapper.getClient()
                    .getRolePolicy(new GetRolePolicyRequest().withRoleName(roleName).withPolicyName(policyName));
            return new PolicyResponse(result.getPolicyDocument());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.attachedPolicies.list",
            path = "{partition}/roles/{roleName}/attached-policies"
    )
    public AttachedPoliciesResponse rolesAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListAttachedRolePoliciesResult result = clientWrapper.getClient().listAttachedRolePolicies(
                    new ListAttachedRolePoliciesRequest()
                            .withRoleName(roleName)
                            .withMarker(page)
            );
            return new AttachedPoliciesResponse(result.getAttachedPolicies(), result.getMarker());
        } catch (Throwable t) {
            return new AttachedPoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "samlProviders.list",
            path = "{partition}/saml-providers"
    )
    public SamlProvidersResponse samlProvidersList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListSAMLProvidersResult result = clientWrapper.getClient().listSAMLProviders(
                    new ListSAMLProvidersRequest()
            );
            return new SamlProvidersResponse(result.getSAMLProviderList());
        } catch (Throwable t) {
            return new SamlProvidersResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "samlProviders.get",
            path = "{partition}/saml-providers/ARN"
    )
    public SamlProviderResponse samlProvidersGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetSAMLProviderResult result = clientWrapper.getClient()
                    .getSAMLProvider(new GetSAMLProviderRequest().withSAMLProviderArn(arn));
            return new SamlProviderResponse(
                    result.getSAMLMetadataDocument(),
                    result.getCreateDate(),
                    result.getValidUntil()
            );
        } catch (Throwable t) {
            return new SamlProviderResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.policies.list",
            path = "{partition}/users/{userName}/policies"
    )
    public PolicyNamesResponse usersPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListUserPoliciesResult result = clientWrapper.getClient().listUserPolicies(
                    new ListUserPoliciesRequest(userName)
                            .withMarker(page)
            );
            return new PolicyNamesResponse(result.getPolicyNames(), result.getMarker());
        } catch (Throwable t) {
            return new PolicyNamesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.policies.get",
            path = "{partition}/users/{userName}/policies/{policyName}"
    )
    public PolicyResponse usersPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("policyName") final String policyName
    ) throws UnsupportedEncodingException, AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final GetUserPolicyResult result = clientWrapper.getClient()
                    .getUserPolicy(new GetUserPolicyRequest(userName, policyName));
            return new PolicyResponse(result.getPolicyDocument());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.attachedPolicies.list",
            path = "{partition}/users/{userName}/attached-policies"
    )
    public AttachedPoliciesResponse usersAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListAttachedUserPoliciesResult result = clientWrapper.getClient().listAttachedUserPolicies(
                    new ListAttachedUserPoliciesRequest()
                            .withUserName(userName)
                            .withMarker(page)
            );
            return new AttachedPoliciesResponse(result.getAttachedPolicies(), result.getMarker());
        } catch (Throwable t) {
            return new AttachedPoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "virtualMfaDevices.list",
            path = "{partition}/virtual-mfa-devices"
    )
    public VirtualMfaDevicesResponse virtualMfaDevicesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonIdentityManagement> clientWrapper = new AmazonClientHelper(credentials).getIdentityManagement(partition)) {
            final ListVirtualMFADevicesResult result = clientWrapper.getClient().listVirtualMFADevices(
                    new ListVirtualMFADevicesRequest()
                            .withMarker(page)
            );
            return new VirtualMfaDevicesResponse(result.getVirtualMFADevices(), result.getMarker());
        } catch (Throwable t) {
            return new VirtualMfaDevicesResponse(AmazonResponse.parse(t));
        }
    }
}
