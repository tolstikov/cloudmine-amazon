package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.PlatformDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class PlatformDescriptionResponse extends AmazonResponse {
    private PlatformDescription platformDescription;

    public PlatformDescription getPlatformDescription() {
        return platformDescription;
    }

    public void setPlatformDescription(final PlatformDescription platformDescription) {
        this.platformDescription = platformDescription;
    }
}
