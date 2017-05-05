package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Organization;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class OrganizationResponse extends AmazonResponse {
    private Organization organization;

    public OrganizationResponse() {
    }

    public OrganizationResponse(final AmazonException exception) {
        super(exception);
    }

    public OrganizationResponse(final Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }
}
