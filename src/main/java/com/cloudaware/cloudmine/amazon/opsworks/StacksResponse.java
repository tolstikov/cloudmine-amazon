package com.cloudaware.cloudmine.amazon.opsworks;

import com.amazonaws.services.opsworks.model.Stack;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StacksResponse extends AmazonResponse {

    private List<Stack> stacks;

    public List<Stack> getStacks() {
        return stacks;
    }

    public void setStacks(final List<Stack> stacks) {
        this.stacks = stacks;
    }
}
