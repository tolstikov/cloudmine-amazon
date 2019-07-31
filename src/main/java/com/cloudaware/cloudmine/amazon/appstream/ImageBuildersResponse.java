package com.cloudaware.cloudmine.amazon.appstream;

import com.amazonaws.services.appstream.model.ImageBuilder;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ImageBuildersResponse extends AmazonResponse {

    private List<ImageBuilder> imageBuilders;

    public List<ImageBuilder> getImageBuilders() {
        return imageBuilders;
    }

    public void setImageBuilders(final List<ImageBuilder> imageBuilders) {
        this.imageBuilders = imageBuilders;
    }
}
