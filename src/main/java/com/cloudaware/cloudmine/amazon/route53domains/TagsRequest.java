package com.cloudaware.cloudmine.amazon.route53domains;

import java.util.Map;

public final class TagsRequest {

    private Map<String, String> tags;

    public TagsRequest(final Map<String, String> tags) {
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
