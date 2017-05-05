package com.cloudaware.cloudmine.amazon.sts;

import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
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

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sts",
        canonicalName = "Sts",
        title = "AWS Security Token Service (STS)",
        description = "Temporary Credentials",
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
public final class StsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "roles.assume",
            path = "{partition}/roles/assume"
    )
    public AssumeRoleResponse rolesAssume(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            final AssumeRoleRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSSecurityTokenService> clientWrapper = new AmazonClientHelper(credentials).getSts(partition)) {
            final AssumeRoleResult result = clientWrapper.getClient().assumeRole(
                    new com.amazonaws.services.securitytoken.model.AssumeRoleRequest()
                            .withDurationSeconds(request.getDurationSeconds())
                            .withExternalId(request.getExternalId())
                            .withPolicy(request.getPolicy())
                            .withRoleArn(request.getRoleArn())
                            .withRoleSessionName(request.getRoleSessionName())
                            .withSerialNumber(request.getSerialNumber())
                            .withTokenCode(request.getTokenCode())
            );
            return new AssumeRoleResponse(result.getCredentials(), result.getAssumedRoleUser(), result.getPackedPolicySize());
        } catch (Throwable t) {
            return new AssumeRoleResponse(AmazonResponse.parse(t));
        }
    }

}
