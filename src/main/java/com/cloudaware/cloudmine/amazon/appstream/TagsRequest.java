package com.cloudaware.cloudmine.amazon.appstream;

import java.util.Map;

public final class TagsRequest {
    private Map<String, String> tags;

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
