package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:48
 */
public final class SamlProviderResponse extends AmazonResponse {
    private String metadataDocument;
    private Date createDate;
    private Date validUntil;

    public SamlProviderResponse() {
    }

    public SamlProviderResponse(final AmazonException exception) {
        super(exception);
    }

    public SamlProviderResponse(final String metadataDocument, final Date createDate, final Date validUntil) {
        this.metadataDocument = metadataDocument;
        this.createDate = createDate;
        this.validUntil = validUntil;
    }

    public String getMetadataDocument() {
        return metadataDocument;
    }

    public void setMetadataDocument(final String metadataDocument) {
        this.metadataDocument = metadataDocument;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(final Date validUntil) {
        this.validUntil = validUntil;
    }
}
