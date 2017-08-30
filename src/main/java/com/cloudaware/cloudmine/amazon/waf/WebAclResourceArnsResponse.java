package com.cloudaware.cloudmine.amazon.waf;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class WebAclResourceArnsResponse extends AmazonResponse {
    private List<String> resourceArns;

    public List<String> getResourceArns() {
        return resourceArns;
    }

    public void setResourceArns(final List<String> resourceArns) {
        this.resourceArns = resourceArns;
    }
}
