package com.cloudaware.cloudmine.amazon.kinesisanalytics;

import com.amazonaws.services.kinesisanalytics.model.ApplicationSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApplicationSummariesResponse extends AmazonResponse {

    private List<ApplicationSummary> applications;

    public List<ApplicationSummary> getApplications() {
        return applications;
    }

    public void setApplications(final List<ApplicationSummary> applications) {
        this.applications = applications;
    }
}
