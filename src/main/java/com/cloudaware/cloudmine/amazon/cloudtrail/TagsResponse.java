package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.ResourceTag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsResponse extends AmazonResponse {

    private List<ResourceTag> tags;

    public List<ResourceTag> getTags() {
        return tags;
    }

    public void setTags(final List<ResourceTag> tags) {
        this.tags = tags;
    }
}
