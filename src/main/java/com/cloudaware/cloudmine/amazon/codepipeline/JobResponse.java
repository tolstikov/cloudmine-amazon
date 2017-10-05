package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.JobDetails;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class JobResponse extends AmazonResponse {

    private JobDetails jobDetails;

    public JobDetails getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(final JobDetails jobDetails) {
        this.jobDetails = jobDetails;
    }
}
