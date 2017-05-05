package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Tag;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * User: urmuzov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class TagsRequest {

    private List<String> resources;
    private List<Tag> tags;

    public TagsRequest() {
    }

    public TagsRequest(final List<String> resources, final List<Tag> tags) {
        this.resources = resources;
        this.tags = tags;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(final List<String> resources) {
        this.resources = resources;
    }

    public TagsRequest withResources(final String... res) {
        if (this.resources == null) {
            this.resources = Lists.newArrayList();
        }

        this.resources.addAll(Lists.newArrayList(res));

        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }
}
