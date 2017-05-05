package com.cloudaware.cloudmine.amazon.s3;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class TagsRequest {
    private Map<String, String> tags;

    public TagsRequest() {
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
