package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:28
 */
public final class TagsResponse extends AmazonResponse {
    private List<Tag> tags;

    public TagsResponse() {
    }

    public TagsResponse(final AmazonException exception) {
        super(exception);
    }

    public TagsResponse(final List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
