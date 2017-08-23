package com.cloudaware.cloudmine.amazon.servicecatalog;

import com.amazonaws.services.servicecatalog.model.ProvisioningArtifactDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

public final class ProvisioningArtifactResponse extends AmazonResponse {
    private ProvisioningArtifactDetail provisioningArtifactDetail;
    private Map<String, String> info;
    private String status;

    public ProvisioningArtifactDetail getProvisioningArtifactDetail() {
        return provisioningArtifactDetail;
    }

    public void setProvisioningArtifactDetail(final ProvisioningArtifactDetail provisioningArtifactDetail) {
        this.provisioningArtifactDetail = provisioningArtifactDetail;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(final Map<String, String> info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
