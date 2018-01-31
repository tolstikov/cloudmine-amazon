package com.cloudaware.cloudmine.amazon.opsworks;

import java.util.Map;

public final class AddTagsRequest {

    private Map<String, String> tags;

    public AddTagsRequest(final Map<String, String> tags) {
        this.tags = tags;
    }

    public AddTagsRequest() {
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
