package com.cloudaware.cloudmine.amazon.rds;

import java.util.List;

public final class RemoveTagsRequest {
    private String resourceArn;
    private List<String> tagKeys;

    public RemoveTagsRequest() {
    }

    public String getResourceArn() {
        return resourceArn;
    }

    public void setResourceArn(final String resourceArn) {
        this.resourceArn = resourceArn;
    }

    public List<String> getTagKeys() {
        return tagKeys;
    }

    public void setTagKeys(final List<String> tagKeys) {
        this.tagKeys = tagKeys;
    }
}
