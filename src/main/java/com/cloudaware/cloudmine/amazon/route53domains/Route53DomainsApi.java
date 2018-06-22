package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.services.route53domains.model.DeleteTagsForDomainRequest;
import com.amazonaws.services.route53domains.model.GetDomainDetailRequest;
import com.amazonaws.services.route53domains.model.GetDomainDetailResult;
import com.amazonaws.services.route53domains.model.GetOperationDetailRequest;
import com.amazonaws.services.route53domains.model.GetOperationDetailResult;
import com.amazonaws.services.route53domains.model.ListDomainsRequest;
import com.amazonaws.services.route53domains.model.ListDomainsResult;
import com.amazonaws.services.route53domains.model.ListOperationsRequest;
import com.amazonaws.services.route53domains.model.ListOperationsResult;
import com.amazonaws.services.route53domains.model.ListTagsForDomainRequest;
import com.amazonaws.services.route53domains.model.ListTagsForDomainResult;
import com.amazonaws.services.route53domains.model.Tag;
import com.amazonaws.services.route53domains.model.UpdateTagsForDomainRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.collect.Lists;

import java.util.List;

@Api(
        name = "route53domains",
        canonicalName = "Route53Domains",
        title = "Amazon Route 53 Domains",
        description = "Scalable Domain Name System",
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
public final class Route53DomainsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.list",
            path = "domains"
    )
    public DomainsResponse domainsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(ListDomainsRequest.class, DomainsResponse.class, credentials).execute((client, request, response) -> {
            final ListDomainsResult result = client.listDomains(request.withMarker(page));
            response.setDomains(result.getDomains());
            response.setNextPage(result.getNextPageMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.get",
            path = "domains/{domainName}"
    )
    public DomainResponse domainsGet(
            @Named("credentials") final String credentials,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(GetDomainDetailRequest.class, DomainResponse.class, credentials).execute((client, request, response) -> {
            final GetDomainDetailResult result = client.getDomainDetail(request.withDomainName(domainName));
            response.setAbuseContactEmail(result.getAbuseContactEmail());
            response.setAbuseContactPhone(result.getAbuseContactPhone());
            response.setAdminContact(result.getAdminContact());
            response.setAdminPrivacy(result.getAdminPrivacy());
            response.setAutoRenew(result.getAutoRenew());
            response.setCreationDate(result.getCreationDate());
            response.setDnsSec(result.getDnsSec());
            response.setDomainName(result.getDomainName());
            response.setExpirationDate(result.getExpirationDate());
            response.setNameservers(result.getNameservers());
            response.setRegistrantContact(result.getRegistrantContact());
            response.setRegistrantPrivacy(result.getRegistrantPrivacy());
            response.setRegistrarName(result.getRegistrarName());
            response.setRegistrarUrl(result.getRegistrarUrl());
            response.setRegistryDomainId(result.getRegistryDomainId());
            response.setReseller(result.getReseller());
            response.setStatusList(result.getStatusList());
            response.setTechContact(result.getTechContact());
            response.setTechPrivacy(result.getTechPrivacy());
            response.setUpdatedDate(result.getUpdatedDate());
            response.setWhoIsServer(result.getWhoIsServer());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.tags.list",
            path = "domains/{domainName}/tags"
    )
    public TagsResponse domainsTagsList(
            @Named("credentials") final String credentials,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(ListTagsForDomainRequest.class, TagsResponse.class, credentials).execute((client, request, response) -> {
            final ListTagsForDomainResult result = client.listTagsForDomain(request.withDomainName(domainName));
            response.setTags(result.getTagList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "domains.tags.update",
            path = "domains/{domainName}/tags/update"
    )
    public AmazonResponse domainsTagsUpdate(
            @Named("credentials") final String credentials,
            @Named("domainName") final String domainName,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(UpdateTagsForDomainRequest.class, AmazonResponse.class, credentials).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            if (request.getTags() != null) {
                request.getTags().forEach((k, v) -> {
                    final Tag tag = new Tag();
                    tag.setKey(k);
                    tag.setValue(v);
                    tags.add(tag);
                });
            }
            client.updateTagsForDomain(r.withDomainName(domainName).withTagsToUpdate(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "domains.tags.delete",
            path = "domains/{domainName}/tags/delete"
    )
    public AmazonResponse domainsTagsDelete(
            @Named("credentials") final String credentials,
            @Named("domainName") final String domainName,
            @Named("tagKey") final List<String> tagKeys
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(DeleteTagsForDomainRequest.class, AmazonResponse.class, credentials).execute((client, request, response) -> {
            client.deleteTagsForDomain(request.withDomainName(domainName).withTagsToDelete(tagKeys));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "operations.list",
            path = "operations"
    )
    public OperationsResponse operationsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(ListOperationsRequest.class, OperationsResponse.class, credentials).execute((client, request, response) -> {
            final ListOperationsResult result = client.listOperations(request.withMarker(page));
            response.setOperations(result.getOperations());
            response.setNextPage(result.getNextPageMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "operations.get",
            path = "operations/{operationId}"
    )
    public OperationResponse operationsGet(
            @Named("credentials") final String credentials,
            @Named("operationId") final String operationId
    ) throws AmazonUnparsedException {
        return Route53DomainsCaller.get(GetOperationDetailRequest.class, OperationResponse.class, credentials).execute((client, request, response) -> {
            final GetOperationDetailResult result = client.getOperationDetail(request.withOperationId(operationId));
            response.setDomainName(result.getDomainName());
            response.setMessage(result.getMessage());
            response.setOperationId(result.getOperationId());
            response.setStatus(result.getStatus());
            response.setSubmittedDate(result.getSubmittedDate());
            response.setType(result.getType());
        });
    }
}
