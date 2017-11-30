package com.cloudaware.cloudmine.amazon.glue;

import com.amazonaws.services.glue.model.JobRun;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class JobRunsResponse extends AmazonResponse {

    private List<JobRun> jobRuns;

    public List<JobRun> getJobRuns() {
        return jobRuns;
    }

    public void setJobRuns(final List<JobRun> jobRuns) {
        this.jobRuns = jobRuns;
    }
}
