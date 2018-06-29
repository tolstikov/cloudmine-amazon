package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.Tag;

import java.util.List;

public final class TagsRequest {

    private List<Tag> tags;

    public TagsRequest(final List<Tag> tags) {
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
