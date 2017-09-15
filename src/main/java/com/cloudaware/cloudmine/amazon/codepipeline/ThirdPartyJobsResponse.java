package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.ThirdPartyJob;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ThirdPartyJobsResponse extends AmazonResponse {

    private List<ThirdPartyJob> thirdPartyJobs;

    public List<ThirdPartyJob> getThirdPartyJobs() {
        return thirdPartyJobs;
    }

    public void setThirdPartyJobs(final List<ThirdPartyJob> thirdPartyJobs) {
        this.thirdPartyJobs = thirdPartyJobs;
    }
}
