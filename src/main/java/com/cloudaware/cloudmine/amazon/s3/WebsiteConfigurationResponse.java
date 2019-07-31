package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.RedirectRule;
import com.amazonaws.services.s3.model.RoutingRule;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class WebsiteConfigurationResponse extends AmazonResponse {

    private boolean enabled;
    private String errorDocument;
    private RedirectRule redirectAllRequestsTo;
    private String indexDocumentSuffix;
    private List<RoutingRule> routingRules;

    WebsiteConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    WebsiteConfigurationResponse(final String errorDocument, final RedirectRule redirectAllRequestsTo, final String indexDocumentSuffix, final List<RoutingRule> routingRules) {
        enabled = true;
        this.errorDocument = errorDocument;
        this.redirectAllRequestsTo = redirectAllRequestsTo;
        this.indexDocumentSuffix = indexDocumentSuffix;
        this.routingRules = routingRules;
    }

    WebsiteConfigurationResponse() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getErrorDocument() {
        return errorDocument;
    }

    public void setErrorDocument(final String errorDocument) {
        this.errorDocument = errorDocument;
    }

    public RedirectRule getRedirectAllRequestsTo() {
        return redirectAllRequestsTo;
    }

    public void setRedirectAllRequestsTo(final RedirectRule redirectAllRequestsTo) {
        this.redirectAllRequestsTo = redirectAllRequestsTo;
    }

    public String getIndexDocumentSuffix() {
        return indexDocumentSuffix;
    }

    public void setIndexDocumentSuffix(final String indexDocumentSuffix) {
        this.indexDocumentSuffix = indexDocumentSuffix;
    }

    public List<RoutingRule> getRoutingRules() {
        return routingRules;
    }

    public void setRoutingRules(final List<RoutingRule> routingRules) {
        this.routingRules = routingRules;
    }
}
