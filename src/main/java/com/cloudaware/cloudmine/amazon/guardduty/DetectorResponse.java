package com.cloudaware.cloudmine.amazon.guardduty;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DetectorResponse extends AmazonResponse {

    private String createdAt;
    private String serviceRole;
    private String status;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public String getServiceRole() {
        return serviceRole;
    }

    public void setServiceRole(final String serviceRole) {
        this.serviceRole = serviceRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
