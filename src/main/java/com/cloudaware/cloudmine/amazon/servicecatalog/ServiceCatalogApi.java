package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.DescribeConstraintRequest;
import com.amazonaws.services.servicecatalog.model.DescribeConstraintResult;
import com.amazonaws.services.servicecatalog.model.DescribePortfolioRequest;
import com.amazonaws.services.servicecatalog.model.DescribePortfolioResult;
import com.amazonaws.services.servicecatalog.model.DescribeProductAsAdminRequest;
import com.amazonaws.services.servicecatalog.model.DescribeProductAsAdminResult;
import com.amazonaws.services.servicecatalog.model.DescribeProvisioningArtifactRequest;
import com.amazonaws.services.servicecatalog.model.DescribeProvisioningArtifactResult;
import com.amazonaws.services.servicecatalog.model.ListConstraintsForPortfolioRequest;
import com.amazonaws.services.servicecatalog.model.ListConstraintsForPortfolioResult;
import com.amazonaws.services.servicecatalog.model.ListPortfolioAccessRequest;
import com.amazonaws.services.servicecatalog.model.ListPortfolioAccessResult;
import com.amazonaws.services.servicecatalog.model.ListPortfoliosForProductRequest;
import com.amazonaws.services.servicecatalog.model.ListPortfoliosForProductResult;
import com.amazonaws.services.servicecatalog.model.ListPortfoliosRequest;
import com.amazonaws.services.servicecatalog.model.ListPortfoliosResult;
import com.amazonaws.services.servicecatalog.model.ListPrincipalsForPortfolioRequest;
import com.amazonaws.services.servicecatalog.model.ListPrincipalsForPortfolioResult;
import com.amazonaws.services.servicecatalog.model.ListProvisioningArtifactsRequest;
import com.amazonaws.services.servicecatalog.model.ListProvisioningArtifactsResult;
import com.amazonaws.services.servicecatalog.model.SearchProductsAsAdminRequest;
import com.amazonaws.services.servicecatalog.model.SearchProductsAsAdminResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "servicecatalog",
        canonicalName = "ServiceCatalog",
        title = "AWS Service Catalog",
        description = "Create and Use Standardized Products",
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
public final class ServiceCatalogApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "products.list",
            path = "{region}/products"
    )
    public ProductsResponse productsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(SearchProductsAsAdminRequest.class, ProductsResponse.class, credentials, region).execute((client, request, response) -> {
            final SearchProductsAsAdminResult result = client.searchProductsAsAdmin(request.withPageToken(page));
            response.setProducts(result.getProductViewDetails());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "products.get",
            path = "{region}/products/PRODUCT_ID"
    )
    public ProductResponse productsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("productId") final String productId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(DescribeProductAsAdminRequest.class, ProductResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeProductAsAdminResult result = client.describeProductAsAdmin(request.withId(productId));
            response.setProductViewDetail(result.getProductViewDetail());
            response.setProvisioningArtifactSummaries(result.getProvisioningArtifactSummaries());
            response.setTags(result.getTags());
            response.setTagOptions(result.getTagOptions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "products.portfolios.list",
            path = "{region}/products/PRODUCT_ID/portfolios"
    )
    public ProtfoliosResponse productsPortfoliosList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("productId") final String productId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListPortfoliosForProductRequest.class, ProtfoliosResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPortfoliosForProductResult result = client.listPortfoliosForProduct(request.withProductId(productId).withPageToken(page));
            response.setPortfolios(result.getPortfolioDetails());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "products.provisioningArtifacts.list",
            path = "{region}/products/PRODUCT_ID/provisioning-artifacts"
    )
    public ProvisioningArtifactsResponse productsProvisioningArtifactsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("productId") final String productId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListProvisioningArtifactsRequest.class, ProvisioningArtifactsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListProvisioningArtifactsResult result = client.listProvisioningArtifacts(request.withProductId(productId));
            response.setProvisioningArtifactDetails(result.getProvisioningArtifactDetails());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "products.provisioningArtifacts.get",
            path = "{region}/products/PRODUCT_ID/provisioning-artifacts/ARTIFACT_ID"
    )
    public ProvisioningArtifactResponse productsProvisioningArtifactsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("productId") final String productId,
            @Named("artifactId") final String artifactId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(DescribeProvisioningArtifactRequest.class, ProvisioningArtifactResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeProvisioningArtifactResult result = client.describeProvisioningArtifact(request.withProductId(productId).withProvisioningArtifactId(artifactId));
            response.setProvisioningArtifactDetail(result.getProvisioningArtifactDetail());
            response.setStatus(result.getStatus());
            response.setInfo(result.getInfo());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "portfolios.list",
            path = "{region}/portfolios"
    )
    public ProtfoliosResponse portfoliosList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListPortfoliosRequest.class, ProtfoliosResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPortfoliosResult result = client.listPortfolios(request.withPageToken(page));
            response.setPortfolios(result.getPortfolioDetails());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "portfolios.get",
            path = "{region}/portfolios/PORTFOLIO_ID"
    )
    public ProtfolioResponse portfoliosGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("portfolioId") final String portfolioId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(DescribePortfolioRequest.class, ProtfolioResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribePortfolioResult result = client.describePortfolio(request.withId(portfolioId));
            response.setPortfolioDetail(result.getPortfolioDetail());
            response.setTagOptions(result.getTagOptions());
            response.setTags(result.getTags());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "portfolios.principals.list",
            path = "{region}/portfolios/PORTFOLIO_ID/principals"
    )
    public ProtfolioPrincipalsResponse portfoliosPrincipalsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("portfolioId") final String portfolioId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListPrincipalsForPortfolioRequest.class, ProtfolioPrincipalsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPrincipalsForPortfolioResult result = client.listPrincipalsForPortfolio(request.withPortfolioId(portfolioId).withPageToken(page));
            response.setPrincipals(result.getPrincipals());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "portfolios.constraints.list",
            path = "{region}/portfolios/PORTFOLIO_ID/constraints"
    )
    public ProtfolioConstraintsResponse portfoliosConstraintsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("portfolioId") final String portfolioId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListConstraintsForPortfolioRequest.class, ProtfolioConstraintsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListConstraintsForPortfolioResult result = client.listConstraintsForPortfolio(request.withPortfolioId(portfolioId).withPageToken(page));
            response.setConstraintDetails(result.getConstraintDetails());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "constraints.get",
            path = "{region}/constraints/CONSTRAINT_ID"
    )
    public ConstraintResponse constraintsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("constraintId") final String constraintId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(DescribeConstraintRequest.class, ConstraintResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConstraintResult result = client.describeConstraint(request.withId(constraintId));
            response.setConstraintDetail(result.getConstraintDetail());
            response.setConstraintParameters(result.getConstraintParameters());
            response.setStatus(result.getStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "portfolios.access.list",
            path = "{region}/portfolios/PORTFOLIO_ID/access"
    )
    public ProtfolioAccessResponse portfoliosAccessList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("portfolioId") final String portfolioId
    ) throws AmazonUnparsedException {
        return ServiceCatalogCaller.get(ListPortfolioAccessRequest.class, ProtfolioAccessResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPortfolioAccessResult result = client.listPortfolioAccess(request.withPortfolioId(portfolioId));
            response.setAccountIds(result.getAccountIds());
        });
    }

}
