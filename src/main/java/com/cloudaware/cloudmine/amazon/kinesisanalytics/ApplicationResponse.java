package com.cloudaware.cloudmine.amazon.kinesisanalytics;

import com.amazonaws.services.kinesisanalytics.model.ApplicationDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ApplicationResponse extends AmazonResponse {

    private ApplicationDetail application;

    public ApplicationDetail getApplication() {
        return application;
    }

    public void setApplication(final ApplicationDetail application) {
        this.application = application;
    }
}
