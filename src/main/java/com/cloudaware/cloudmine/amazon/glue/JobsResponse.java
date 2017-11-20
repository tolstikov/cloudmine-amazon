package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.Job;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobsResponse extends AmazonResponse {

    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(final List<Job> jobs) {
        this.jobs = jobs;
    }
}
