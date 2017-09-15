package com.cloudaware.cloudmine.amazon.codepipeline;

import com.amazonaws.services.codepipeline.model.ThirdPartyJobDetails;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ThirdPartyJobResponse extends AmazonResponse {

    private ThirdPartyJobDetails thirdPartyJobDetails;

    public ThirdPartyJobDetails getThirdPartyJobDetails() {
        return thirdPartyJobDetails;
    }

    public void setThirdPartyJobDetails(final ThirdPartyJobDetails thirdPartyJobDetails) {
        this.thirdPartyJobDetails = thirdPartyJobDetails;
    }
}
