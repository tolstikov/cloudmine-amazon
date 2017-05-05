package com.cloudaware.cloudmine.amazon.elb;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * User: tolstikov
 * Date: 7/10/13
 * Time: 4:12 PM
 */
public final class TagsRequest {
    private List<String> loadBalancerNames;
    private Map<String, String> tags;

    public TagsRequest() {
    }

    public TagsRequest(final List<String> loadBalancerNames, final Map<String, String> tags) {
        this.loadBalancerNames = loadBalancerNames;
        this.tags = tags;
    }

    public List<String> getLoadBalancerNames() {
        return loadBalancerNames;
    }

    public void setLoadBalancerNames(final List<String> loadBalancerNames) {
        this.loadBalancerNames = loadBalancerNames;
    }

    public TagsRequest withLoadBalancerNames(final String... loadBalancerNamesIn) {
        if (this.loadBalancerNames == null) {
            this.loadBalancerNames = Lists.newArrayList();
        }

        this.loadBalancerNames.addAll(Lists.newArrayList(loadBalancerNamesIn));

        return this;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
