package com.cloudaware.cloudmine.amazon.route53;

import java.util.Map;
import java.util.Set;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private Map<String, String> addTags;
    private Set<String> removeTagKeys;

    public TagsRequest() {
    }

    public Map<String, String> getAddTags() {
        return addTags;
    }

    public void setAddTags(final Map<String, String> addTags) {
        this.addTags = addTags;
    }

    public Set<String> getRemoveTagKeys() {
        return removeTagKeys;
    }

    public void setRemoveTagKeys(final Set<String> removeTagKeys) {
        this.removeTagKeys = removeTagKeys;
    }
}
