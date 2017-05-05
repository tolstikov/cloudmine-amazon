package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Image;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:27
 */
public final class ImagesResponse extends AmazonResponse {
    private List<Image> images;

    public ImagesResponse() {
    }

    public ImagesResponse(final AmazonException exception) {
        super(exception);
    }

    public ImagesResponse(final List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(final List<Image> images) {
        this.images = images;
    }
}
