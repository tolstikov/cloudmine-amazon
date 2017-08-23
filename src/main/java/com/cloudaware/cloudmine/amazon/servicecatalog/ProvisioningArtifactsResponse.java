package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ProvisioningArtifactDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ProvisioningArtifactsResponse extends AmazonResponse {
    private List<ProvisioningArtifactDetail> provisioningArtifactDetails;

    public List<ProvisioningArtifactDetail> getProvisioningArtifactDetails() {
        return provisioningArtifactDetails;
    }

    public void setProvisioningArtifactDetails(final List<ProvisioningArtifactDetail> provisioningArtifactDetails) {
        this.provisioningArtifactDetails = provisioningArtifactDetails;
    }
}
