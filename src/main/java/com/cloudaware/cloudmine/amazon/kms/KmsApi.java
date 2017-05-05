package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.model.DescribeKeyRequest;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import com.amazonaws.services.kms.model.GetKeyPolicyRequest;
import com.amazonaws.services.kms.model.GetKeyRotationStatusRequest;
import com.amazonaws.services.kms.model.GetKeyRotationStatusResult;
import com.amazonaws.services.kms.model.ListAliasesRequest;
import com.amazonaws.services.kms.model.ListAliasesResult;
import com.amazonaws.services.kms.model.ListGrantsRequest;
import com.amazonaws.services.kms.model.ListGrantsResult;
import com.amazonaws.services.kms.model.ListKeyPoliciesRequest;
import com.amazonaws.services.kms.model.ListKeyPoliciesResult;
import com.amazonaws.services.kms.model.ListKeysRequest;
import com.amazonaws.services.kms.model.ListKeysResult;
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
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "kms",
        canonicalName = "Kms",
        title = "AWS Key Management Service",
        description = "Managed Creation and Control of Encryption Keys",
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
public final class KmsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "aliases.list",
            path = "{region}/aliases"
    )
    public AliasesResponse aliasesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final ListAliasesResult result = clientWrapper.getClient().listAliases(
                    new ListAliasesRequest()
                            .withMarker(page)
            );
            return new AliasesResponse(result.getAliases(), result.getNextMarker());
        } catch (Throwable t) {
            return new AliasesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.list",
            path = "{region}/keys"
    )
    public KeysResponse keysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final ListKeysResult result = clientWrapper.getClient().listKeys(
                    new ListKeysRequest()
                            .withMarker(page)
            );
            return new KeysResponse(result.getKeys(), result.getNextMarker());
        } catch (Throwable t) {
            return new KeysResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.get",
            path = "{region}/keys/{keyId}"
    )
    public KeyResponse keysGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final DescribeKeyResult describeKeyResult = clientWrapper.getClient().describeKey(new DescribeKeyRequest().withKeyId(keyId));
            return new KeyResponse(describeKeyResult.getKeyMetadata());
        } catch (Throwable t) {
            return new KeyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.grants.list",
            path = "{region}/keys/{keyId}/grants"
    )
    public GrantsResponse keysGrantsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final ListGrantsResult result = clientWrapper.getClient().listGrants(
                    new ListGrantsRequest()
                            .withKeyId(keyId)
                            .withMarker(page)
            );
            return new GrantsResponse(result.getGrants(), result.getNextMarker());
        } catch (Throwable t) {
            return new GrantsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.policies.list",
            path = "{region}/keys/{keyId}/policies"
    )
    public PoliciesResponse keysPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final ListKeyPoliciesResult result = clientWrapper.getClient().listKeyPolicies(
                    new ListKeyPoliciesRequest()
                            .withKeyId(keyId)
                            .withMarker(page)
            );
            return new PoliciesResponse(result.getPolicyNames(), result.getNextMarker());
        } catch (Throwable t) {
            return new PoliciesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.policies.get",
            path = "{region}/keys/{keyId}/policies/{policyName}"
    )
    public PolicyResponse keysPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            return new PolicyResponse(clientWrapper.getClient().getKeyPolicy(new GetKeyPolicyRequest().withKeyId(keyId).withPolicyName(policyName)).getPolicy());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.rotationStatus.get",
            path = "{region}/keys/{keyId}/rotation-status"
    )
    public RotationStatusResponse keysGotationStatusGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSKMS> clientWrapper = new AmazonClientHelper(credentials).getKms(region)) {
            final GetKeyRotationStatusResult result = clientWrapper.getClient().getKeyRotationStatus(new GetKeyRotationStatusRequest().withKeyId(keyId));
            return new RotationStatusResponse(result == null ? false : result.getKeyRotationEnabled());
        } catch (Throwable t) {
            return new RotationStatusResponse(AmazonResponse.parse(t));
        }
    }
}
