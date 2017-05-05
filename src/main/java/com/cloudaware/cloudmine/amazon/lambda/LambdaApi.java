package com.cloudaware.cloudmine.amazon.lambda;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.model.GetFunctionRequest;
import com.amazonaws.services.lambda.model.GetFunctionResult;
import com.amazonaws.services.lambda.model.GetPolicyRequest;
import com.amazonaws.services.lambda.model.ListAliasesRequest;
import com.amazonaws.services.lambda.model.ListAliasesResult;
import com.amazonaws.services.lambda.model.ListEventSourceMappingsRequest;
import com.amazonaws.services.lambda.model.ListEventSourceMappingsResult;
import com.amazonaws.services.lambda.model.ListFunctionsRequest;
import com.amazonaws.services.lambda.model.ListFunctionsResult;
import com.amazonaws.services.lambda.model.ListVersionsByFunctionRequest;
import com.amazonaws.services.lambda.model.ListVersionsByFunctionResult;
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
        name = "lambda",
        canonicalName = "Lambda",
        title = "AWS Lambda",
        description = "Run Your Code in Response to Events",
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
public final class LambdaApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.list",
            path = "{region}/functions"
    )
    public FunctionsResponse functionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            final ListFunctionsResult result = clientWrapper.getClient().listFunctions(
                    new ListFunctionsRequest()
                            .withMarker(page)
            );
            return new FunctionsResponse(result.getFunctions(), result.getNextMarker());
        } catch (Throwable t) {
            return new FunctionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.get",
            path = "{region}/functions/FUNCTION_NAME"
    )
    public FunctionResponse functionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            final GetFunctionResult result = clientWrapper.getClient().getFunction(new GetFunctionRequest().withFunctionName(functionName));
            return new FunctionResponse(result.getConfiguration(), result.getCode());
        } catch (Throwable t) {
            return new FunctionResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.versions.list",
            path = "{region}/functions/FUNCTION_NAME/versions"
    )
    public VersionsResponse functionsVersionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            final ListVersionsByFunctionResult result = clientWrapper.getClient().listVersionsByFunction(
                    new ListVersionsByFunctionRequest()
                            .withFunctionName(functionName)
                            .withMarker(page)
            );
            return new VersionsResponse(result.getVersions(), result.getNextMarker());
        } catch (Throwable t) {
            return new VersionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.aliases.list",
            path = "{region}/functions/FUNCTION_NAME/aliases"
    )
    public AliasesResponse functionsAliasesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            final ListAliasesResult result = clientWrapper.getClient().listAliases(
                    new ListAliasesRequest()
                            .withFunctionName(functionName)
                            .withMarker(page)
            );
            return new AliasesResponse(result.getAliases(), result.getNextMarker());
        } catch (Throwable t) {
            return new AliasesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "functions.policy.get",
            path = "{region}/functions/FUNCTION_NAME/policy"
    )
    public PolicyResponse functionsPolicyGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("functionName") final String functionName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            return new PolicyResponse(clientWrapper.getClient().getPolicy(new GetPolicyRequest().withFunctionName(functionName)).getPolicy());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "eventSourceMappings.list",
            path = "{region}/event-source-mappings"
    )
    public EventSourceMappingsResponse eventSourceMappingsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSLambda> clientWrapper = new AmazonClientHelper(credentials).getLambda(region)) {
            final ListEventSourceMappingsResult result = clientWrapper.getClient().listEventSourceMappings(
                    new ListEventSourceMappingsRequest()
                            .withMarker(page)
            );
            return new EventSourceMappingsResponse(result.getEventSourceMappings(), result.getNextMarker());
        } catch (Throwable t) {
            return new EventSourceMappingsResponse(AmazonResponse.parse(t));
        }
    }
}
