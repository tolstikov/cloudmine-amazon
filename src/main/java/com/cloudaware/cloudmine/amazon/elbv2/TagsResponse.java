package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.TagDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TagsResponse extends AmazonResponse {

    private List<TagDescription> tags;

    public List<TagDescription> getTags() {
        return tags;
    }

    public void setTags(final List<TagDescription> tags) {
        this.tags = tags;
    }
}
