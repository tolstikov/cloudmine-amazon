package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.services.route53domains.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsResponse extends AmazonResponse {

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
