package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.ApplicationInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApplicationsResponse extends AmazonResponse {

    private List<ApplicationInfo> applications;

    public List<ApplicationInfo> getApplications() {
        return applications;
    }

    public void setApplications(final List<ApplicationInfo> applications) {
        this.applications = applications;
    }
}
