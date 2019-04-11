package com.cloudaware.cloudmine.amazon.appmesh;

import com.amazonaws.services.appmesh.model.TagRef;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsListResponse extends AmazonResponse {
    private List<TagRef> tags;

    public List<TagRef> getTags() {
        return tags;
    }

    public void setTags(final List<TagRef> tags) {
        this.tags = tags;
    }
}
