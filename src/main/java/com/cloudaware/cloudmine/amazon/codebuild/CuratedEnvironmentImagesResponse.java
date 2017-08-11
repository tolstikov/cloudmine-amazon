package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.EnvironmentPlatform;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CuratedEnvironmentImagesResponse extends AmazonResponse {

    private List<EnvironmentPlatform> curatedEnvironmentImages;

    public List<EnvironmentPlatform> getCuratedEnvironmentImages() {
        return curatedEnvironmentImages;
    }

    public void setCuratedEnvironmentImages(final List<EnvironmentPlatform> curatedEnvironmentImages) {
        this.curatedEnvironmentImages = curatedEnvironmentImages;
    }
}
